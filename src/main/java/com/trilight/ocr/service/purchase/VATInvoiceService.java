package com.trilight.ocr.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.pojo.VATInvoiceDO;
import org.springframework.web.multipart.MultipartFile;

public interface VATInvoiceService extends IService<VATInvoiceDO> {

    R<Void> uploadInvoiceFiles(MultipartFile[] files);
}
