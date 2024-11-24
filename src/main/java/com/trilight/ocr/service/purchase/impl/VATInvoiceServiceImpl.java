package com.trilight.ocr.service.purchase.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.trilight.ocr.client.erp.ErpClient;
import com.trilight.ocr.client.erp.model.*;
import com.trilight.ocr.client.minio.MinioService;
import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import com.trilight.ocr.mapper.purchase.VATInvoiceMapper;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.pojo.purchase.CommodityDO;
import com.trilight.ocr.model.pojo.purchase.VATInvoiceDO;
import com.trilight.ocr.service.purchase.VATInvoiceService;
import com.trilight.ocr.utils.FileProcessUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.text.CharSequenceUtil.isEmpty;

@RequiredArgsConstructor
@Service
public class VATInvoiceServiceImpl extends ServiceImpl<VATInvoiceMapper, VATInvoiceDO> implements VATInvoiceService {

    private final MinioService minioService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public R<Void> uploadInvoiceFiles(MultipartFile[] files) {
        try {
            List<VATInvoiceDTO> vatInvoiceDTOList = new ArrayList<>();
            for (MultipartFile file : files) {
                String base64Content = FileProcessUtil.processFile(file);
                VATInvoiceDTO vatInvoiceDTO = BaiduOcrClient.parseVatInvoice(base64Content);
                String ossFileName = minioService.uploadFile(file);
                if (vatInvoiceDTO == null) {
                    throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
                }
                vatInvoiceDTO.setOssFileName(ossFileName);
                vatInvoiceDTOList.add(vatInvoiceDTO);
            }

            List<VATInvoiceDO> vatInvoiceDOList = BeanUtil.copyToList(vatInvoiceDTOList, VATInvoiceDO.class);
            for (VATInvoiceDO vatInvoiceDO : vatInvoiceDOList) {
                if (!isEmpty(vatInvoiceDO.getSellerName())) {
                    ErpRequest<ResultParameter<Supplier>> request = ErpClient.request(
                            buildErpRequest(vatInvoiceDO.getSellerName()), "yf.oapi.supplier.query.get",
                            Supplier.class);
                    List<Supplier> rows = request.getStdData().getParameter().getQueryResult().getRows();
                    Supplier supplier = rows.get(0);
                    vatInvoiceDO.setSupplierNo(supplier.getSupplierNo());
                }
            }

            Db.saveBatch(vatInvoiceDOList);
            List<CommodityDO> commodityDOList = vatInvoiceDOList.stream()
                    .flatMap(vatInvoiceDO -> vatInvoiceDO.getCommodityList().stream()
                            .peek(commodityDO -> commodityDO.setVatInvoiceId(vatInvoiceDO.getVatInvoiceId()))
                    ).toList();
            Db.saveBatch(commodityDOList);
            return R.ok();
        } catch (IOException e) {
            throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ErpRequest<RequestParameter> buildErpRequest(String supplierFullName) {
        StdData<RequestParameter> stdData = new StdData<>();
        ErpRequest<RequestParameter> erpRequest = new ErpRequest<>();
        erpRequest.setStdData(stdData);
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setPageNo(1);
        requestParameter.setPageSize(100000);
        Conditions condition = new Conditions();
        Field field1 = new Field();
        field1.setFieldName("supplier_fullname");
        field1.setValue(supplierFullName);
        field1.setOperator("=");
        List<Field> fieldList = new ArrayList<>();
        fieldList.add(field1);
        condition.setFields(fieldList);
        condition.setOperator("AND");
        stdData.setParameter(requestParameter);
        requestParameter.setConditions(condition);
        return erpRequest;
    }

    @Override
    public PageResult<VATInvoiceDTO> pageVATInvoice(PageQuery pageQuery, VATInvoiceDTO vatInvoiceDTO,
                                                    LocalDate startDate, LocalDate endDate,
                                                    LocalDateTime createStartTime, LocalDateTime createEndTime) {
        IPage<VATInvoiceDTO> vatInvoiceDTOIPage = baseMapper.pageVATInvoice(pageQuery.build(), vatInvoiceDTO, startDate,
                endDate, createStartTime, createEndTime);
        return PageResult.build(vatInvoiceDTOIPage);
    }

    @Override
    public void updateVATInvoice(Long vatInvoiceId, VATInvoiceDTO vatInvoiceDTO) {
        VATInvoiceDO vatInvoiceDO = BeanUtil.copyProperties(vatInvoiceDTO, VATInvoiceDO.class);
        vatInvoiceDO.setVatInvoiceId(vatInvoiceId);
        updateById(vatInvoiceDO);
    }

    @Override
    public void downloadInvoice(Long vatInvoiceId, HttpServletResponse response) {
        try {
            VATInvoiceDO vatInvoiceDO = getById(vatInvoiceId);
            if (vatInvoiceDO == null || vatInvoiceDO.getOssFileName() == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invoice not found or file missing.");
                return;
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=delivery_files_" + vatInvoiceDO.getVatInvoiceId() + ".pdf");

            try (InputStream inputStream = minioService.downloadFile(vatInvoiceDO.getOssFileName());
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
