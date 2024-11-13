package com.trilight.ocr.service.purchase.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.client.minio.MinioService;
import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import com.trilight.ocr.mapper.DeliveryOrderMapper;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.model.pojo.DeliveryConfirmFileDO;
import com.trilight.ocr.model.pojo.DeliveryOrderDO;
import com.trilight.ocr.model.pojo.DeliveryOrderDetailDO;
import com.trilight.ocr.service.purchase.DeliveryConfirmFileService;
import com.trilight.ocr.service.purchase.DeliveryOrderDetailService;
import com.trilight.ocr.service.purchase.DeliveryOrderService;
import com.trilight.ocr.utils.FileProcessUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
}
