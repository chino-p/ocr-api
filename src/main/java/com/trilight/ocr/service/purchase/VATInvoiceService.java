package com.trilight.ocr.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.pojo.purchase.VATInvoiceDO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface VATInvoiceService extends IService<VATInvoiceDO> {

    R<Void> uploadInvoiceFiles(MultipartFile[] files);

    PageResult<VATInvoiceDTO> pageVATInvoice(PageQuery pageQuery, VATInvoiceDTO vatInvoiceDTO, LocalDate startDate, LocalDate endDate, LocalDateTime createStartTime, LocalDateTime createEndTime);

    void updateVATInvoice(Long vatInvoiceId, VATInvoiceDTO vatInvoiceDTO);

    void downloadInvoice(Long vatInvoiceId, HttpServletResponse response);
}
