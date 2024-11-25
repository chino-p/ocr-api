package com.trilight.ocr.service.warehouse.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.client.erp.ErpClient;
import com.trilight.ocr.client.erp.model.*;
import com.trilight.ocr.client.minio.MinioService;
import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.mapper.warehouse.GoodsReceiptMapper;
import com.trilight.ocr.model.dto.warehouse.GoodsReceiptDTO;
import com.trilight.ocr.model.pojo.warehouse.FailedFileDO;
import com.trilight.ocr.model.pojo.warehouse.GoodsReceiptDO;
import com.trilight.ocr.service.warehouse.FailedFileService;
import com.trilight.ocr.service.warehouse.GoodsReceiptService;
import com.trilight.ocr.utils.FileProcessUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoodsReceiptServiceImpl extends ServiceImpl<GoodsReceiptMapper, GoodsReceiptDO>
        implements GoodsReceiptService {

    private final FailedFileService failedFileService;
    private final MinioService minioService;

    @Transactional
    @Override
    public R<Void> uploadDeliverySheet(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String ossFileName = "";
            try {
                String base64Content = FileProcessUtil.processPdfToBase64(file.getInputStream());
                String code = BaiduOcrClient.parseDeliveryCode(base64Content);
                ossFileName = minioService.uploadFile(file);
                GoodsReceiptDO goodsReceiptDO = new GoodsReceiptDO();
                StdData<RequestParameter> stdData = new StdData<>();
                ErpRequest<RequestParameter> erpRequest = new ErpRequest<>();
                erpRequest.setStdData(stdData);
                RequestParameter requestParameter = new RequestParameter();
                requestParameter.setPageNo(1);
                requestParameter.setPageSize(100000);
                Conditions condition = new Conditions();
                Field field1 = new Field();
                field1.setFieldName("supplier_order_no");
                field1.setValue(code);
                field1.setOperator("=");
                List<Field> fieldList = new ArrayList<>();
                fieldList.add(field1);
                condition.setFields(fieldList);
                condition.setOperator("AND");
                stdData.setParameter(requestParameter);
                requestParameter.setConditions(condition);
                ErpRequest<ResultParameter<GoodsReceipt>> request = ErpClient.request(erpRequest,
                        "yf.oapi.purchase.receipt.data.query.get", GoodsReceipt.class);
                List<GoodsReceipt> rows = request.getStdData().getParameter().getQueryResult().getRows();
                if (rows != null && !rows.isEmpty()) {
                    for (GoodsReceipt goodsReceipt : rows) {
                        goodsReceiptDO.setDocNo(goodsReceipt.getDocNo());
                        goodsReceiptDO.setDocTypeNo(goodsReceipt.getDocTypeNo());
                        goodsReceiptDO.setDeliveryNum(goodsReceipt.getSupplierOrderNo());
                        goodsReceiptDO.setSupplierNo(goodsReceipt.getSupplierNo());
                        goodsReceiptDO.setOssFileName(ossFileName);
                        save(goodsReceiptDO);
                    }
                } else {
                    FailedFileDO failedFileDO = new FailedFileDO();
                    failedFileDO.setFileName(originalFilename);
                    failedFileDO.setOssFileName(ossFileName);
                    failedFileService.save(failedFileDO);
                }
            } catch (Exception e) {
                FailedFileDO failedFileDO = new FailedFileDO();
                failedFileDO.setFileName(originalFilename);
                failedFileDO.setOssFileName(ossFileName);
                failedFileDO.setReason(e.getMessage());
                failedFileService.save(failedFileDO);
                log.error("文件上传失败");
            }
        }

        return R.ok();
    }

    @Override
    public IPage<GoodsReceiptDTO> pageGoodsReceipt(PageQuery pageQuery) {
        Page<GoodsReceiptDO> page = page(pageQuery.build());
        Page<GoodsReceiptDTO> goodsReceipt = new Page<>();
        goodsReceipt.setSize(page.getSize());
        goodsReceipt.setTotal(page.getTotal());
        goodsReceipt.setRecords(BeanUtil.copyToList(page.getRecords(), GoodsReceiptDTO.class));
        return goodsReceipt;
    }

    @Override
    public void downloadDeliverySheet(Long id, HttpServletResponse response) {
        try {
            GoodsReceiptDO goodsReceiptDO = getById(id);
            if (goodsReceiptDO == null || goodsReceiptDO.getOssFileName() == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invoice not found or file missing.");
                return;
            }

            response.setContentType("image/jpeg");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + goodsReceiptDO.getOssFileName());

            try (InputStream inputStream = minioService.downloadFile(goodsReceiptDO.getOssFileName());
                 OutputStream outputStream = response.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            }

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
