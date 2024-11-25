package com.trilight.ocr.controller.warehouse;


import com.trilight.ocr.common.model.R;
import com.trilight.ocr.service.warehouse.GoodsReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/goodsReceipt")
@RequiredArgsConstructor
@RestController
public class GoodsReceiptController {

    private final GoodsReceiptService goodsReceiptService;

    @PostMapping("uploadScan")
    public R<Void> uploadScan(@RequestParam("files")  MultipartFile[] files) {
        return goodsReceiptService.uploadDeliverySheet(files);
    }

    @PostMapping("testCode")
    public R<Void> testCode(MultipartFile file) {
        goodsReceiptService.testCode(file);
        return R.ok();
    }
}
