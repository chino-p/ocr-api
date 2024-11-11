package com.trilight.ocr.controller.purchase;

import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.service.purchase.VATInvoiceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("page")
    public PageResult<VATInvoiceDTO> pageVATInvoice(PageQuery pageQuery, VATInvoiceDTO vatInvoiceDTO) {
        return vatInvoiceService.pageVATInvoice(pageQuery, vatInvoiceDTO);
    }

    @PutMapping("/{vatInvoiceId}")
    public R<Void> updateVATInvoice(@PathVariable Long vatInvoiceId, @RequestBody VATInvoiceDTO vatInvoiceDTO) {
        vatInvoiceService.updateVATInvoice(vatInvoiceId, vatInvoiceDTO);
        return R.ok();
    }

    @DeleteMapping("/{vatInvoiceId}")
    public R<Void> deleteVATInvoice(@PathVariable Long vatInvoiceId) {
         vatInvoiceService.removeById(vatInvoiceId);
        return R.ok();
    }
}