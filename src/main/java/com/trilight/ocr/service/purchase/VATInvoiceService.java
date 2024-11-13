package com.trilight.ocr.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.pojo.VATInvoiceDO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface VATInvoiceService extends IService<VATInvoiceDO> {

    R<Void> uploadInvoiceFiles(MultipartFile[] files);

    PageResult<VATInvoiceDTO> pageVATInvoice(PageQuery pageQuery, VATInvoiceDTO vatInvoiceDTO, LocalDate startDate, LocalDate endDate);

    void updateVATInvoice(Long vatInvoiceId, VATInvoiceDTO vatInvoiceDTO);
}
