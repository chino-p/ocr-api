package com.trilight.ocr.service.purchase.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.client.minio.MinioService;
import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import com.trilight.ocr.mapper.purchase.DeliveryOrderMapper;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.model.pojo.purchase.DeliveryConfirmFileDO;
import com.trilight.ocr.model.pojo.purchase.DeliveryOrderDO;
import com.trilight.ocr.model.pojo.purchase.DeliveryOrderDetailDO;
import com.trilight.ocr.service.purchase.DeliveryConfirmFileService;
import com.trilight.ocr.service.purchase.DeliveryOrderDetailService;
import com.trilight.ocr.service.purchase.DeliveryOrderService;
import com.trilight.ocr.utils.FileProcessUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RequiredArgsConstructor
@Service
public class DeliveryOrderServiceImpl extends ServiceImpl<DeliveryOrderMapper, DeliveryOrderDO> implements
        DeliveryOrderService {

    private final DeliveryOrderDetailService deliveryOrderDetailService;
    private final DeliveryConfirmFileService deliveryConfirmFileService;
    private final MinioService minioService;

    @Override
    public PageResult<DeliveryOrderDTO> pageDeliveryOrder(PageQuery pageQuery, DeliveryOrderDTO deliveryOrderDTO) {
        return PageResult.build(baseMapper.pageDeliveryOrder(pageQuery.build(), deliveryOrderDTO));
    }

    @Override
    public void parseCode(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            if (fileName == null || (!fileName.endsWith(".pdf") && !fileName.endsWith(".jpg"))) {
                throw new BizException(BizCodeEnum.SUPPORT_PDF_ONLY);
            }
        }
        for (MultipartFile file : files) {
            try {
                String base64Content = FileProcessUtil.processPdfToBase64(file.getInputStream());
                String code = BaiduOcrClient.parseCode(base64Content);
                List<DeliveryOrderDetailDO> deliveryOrderDetailDOList = deliveryOrderDetailService.list(
                        new QueryWrapper<DeliveryOrderDetailDO>().eq("tracking_no", code));

                if (!deliveryOrderDetailDOList.isEmpty()) {
                    DeliveryOrderDetailDO deliveryOrderDetailDO = deliveryOrderDetailDOList.get(0);
                    DeliveryOrderDO deliveryOrderDO = getById(deliveryOrderDetailDO.getHeaderId());
                    String ossFileName = minioService.uploadFile(file);
                    DeliveryConfirmFileDO deliveryConfirmFileDO = new DeliveryConfirmFileDO();
                    deliveryConfirmFileDO.setDeliveryId(deliveryOrderDO.getId());
                    deliveryConfirmFileDO.setOssFileName(ossFileName);
                    deliveryConfirmFileDO.setCreateTime(LocalDateTime.now());
                    deliveryConfirmFileDO.setFileName(file.getOriginalFilename());
                    deliveryConfirmFileService.save(deliveryConfirmFileDO);
                }

                // if (deliveryOrderDetailDOList.isEmpty()) {
                //     throw new BizException(BizCodeEnum.TRACKING_NO_NOT_FOUND);
                // }
            } catch (Exception e) {
                throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
            }
        }
    }

    @Override
    public void downloadConfirmFile(Long id, HttpServletResponse response) {
        try {
            // 查询数据库获取文件列表
            List<DeliveryConfirmFileDO> deliveryConfirmFileDOList = deliveryConfirmFileService.list(
                    new QueryWrapper<DeliveryConfirmFileDO>().eq("delivery_id", id));

            // 设置响应头
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=delivery_files_" + id + ".zip");

            // 创建 ZIP 输出流
            try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
                for (DeliveryConfirmFileDO deliveryConfirmFileDO : deliveryConfirmFileDOList) {
                    // 从 MinIO 下载文件流
                    try (InputStream inputStream = minioService.downloadFile(deliveryConfirmFileDO.getOssFileName())) {
                        // 使用文件名作为 ZIP 条目名，确保唯一性
                        String zipEntryName = deliveryConfirmFileDO.getOssFileName();
                        zipOut.putNextEntry(new ZipEntry(zipEntryName));

                        // 将文件内容写入 ZIP
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            zipOut.write(buffer, 0, bytesRead);
                        }

                        zipOut.closeEntry();
                    } catch (Exception e) {
                        // 打印单个文件处理错误，不影响其他文件
                        e.printStackTrace();
                    }
                }
            }

            // 确保响应流刷新
            response.flushBuffer();
        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Failed to download files: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
