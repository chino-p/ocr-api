package com.trilight.ocr.service.warehouse.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.client.erp.ErpClient;
import com.trilight.ocr.client.erp.model.*;
import com.trilight.ocr.client.minio.MinioService;
import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.exception.BizException;
import com.trilight.ocr.mapper.warehouse.GoodsReceiptMapper;
import com.trilight.ocr.model.pojo.warehouse.FailedFileDO;
import com.trilight.ocr.model.pojo.warehouse.GoodsReceiptDO;
import com.trilight.ocr.service.warehouse.FailedFileService;
import com.trilight.ocr.service.warehouse.GoodsReceiptService;
import com.trilight.ocr.utils.FileProcessUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        List<GoodsReceiptDO> goodsReceiptDOList = new ArrayList<>();
        List<FailedFileDO> failedFileDOList = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            try {
                String base64Content = FileProcessUtil.processPdfToBase64(file.getInputStream());
                String code = BaiduOcrClient.parseDeliveryCode(base64Content);
                String ossFileName = minioService.uploadFile(file);
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
                stdData.setParameter(requestParameter);
                requestParameter.setConditions(condition);
                ErpRequest<ResultParameter<GoodsReceipt>> request = ErpClient.request(erpRequest,
                        "yf.oapi.purchase.receipt.data.query.get", GoodsReceipt.class);
                List<GoodsReceipt> rows = request.getStdData().getParameter().getQueryResult().getRows();
                if (rows != null && !rows.isEmpty()) {
                    GoodsReceipt goodsReceipt = rows.get(0);
                    goodsReceiptDO.setDocNo(goodsReceipt.getDocNo());
                    goodsReceiptDO.setDocTypeNo(goodsReceipt.getDocTypeNo());
                    goodsReceiptDO.setDeliveryNum(goodsReceipt.getSupplierOrderNo());
                    goodsReceiptDO.setSupplierNo(goodsReceipt.getSupplierNo());
                    goodsReceiptDO.setOssFileName(ossFileName);
                    goodsReceiptDOList.add(goodsReceiptDO);
                }
                for (GoodsReceiptDO receiptDO : goodsReceiptDOList) {
                    save(receiptDO);
                }
            } catch (Exception e) {
                FailedFileDO failedFileDO = new FailedFileDO();
                failedFileDO.setFileName(originalFilename);
                failedFileDO.setOssFileName(e.getMessage());
                failedFileService.save(failedFileDO);
                log.error("文件上传失败");
            }
        }

        return R.ok();
    }

    @Override
    public void testCode(MultipartFile file) {

        List<FailedFileDO> failedFileDOList = new ArrayList<>();
        try {
            String base64Content = FileProcessUtil.processPdfToBase64(file.getInputStream());
            String code = BaiduOcrClient.parseDeliveryCode(base64Content);
        } catch (BizException | IOException e) {
            System.out.println("解析失败");
        }

    }
}
