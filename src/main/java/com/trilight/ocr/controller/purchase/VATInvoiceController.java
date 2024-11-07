package com.trilight.ocr.controller.purchase;

import com.trilight.ocr.common.model.R;
import com.trilight.ocr.service.purchase.VATInvoiceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/vat_invoice")
@RequiredArgsConstructor
@RestController
@Validated
public class VATInvoiceController {

    private final VATInvoiceService vatInvoiceService;

    @PostMapping("/upload")
    public R<Void> uploadFiles(@RequestParam("files") @NotNull(message = "文件不能为空") MultipartFile[] files) {
        return vatInvoiceService.uploadInvoiceFiles(files);
    }
}