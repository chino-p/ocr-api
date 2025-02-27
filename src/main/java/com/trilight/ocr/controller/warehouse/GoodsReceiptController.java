package com.trilight.ocr.controller.warehouse;


import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.warehouse.FailedFileDTO;
import com.trilight.ocr.model.dto.warehouse.GoodsReceiptDTO;
import com.trilight.ocr.model.dto.warehouse.GoodsReceiptDetailDTO;
import com.trilight.ocr.service.warehouse.FailedFileService;
import com.trilight.ocr.service.warehouse.GoodsReceiptService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/goodsReceipt")
@RequiredArgsConstructor
@RestController
public class GoodsReceiptController {

    private final GoodsReceiptService goodsReceiptService;
    private final FailedFileService failedFileService;

    @PostMapping("uploadScan")
    public R<Void> uploadScan(@RequestParam("files") MultipartFile[] files) {
        return goodsReceiptService.uploadDeliverySheet(files);
    }

    @GetMapping("page")
    public PageResult<GoodsReceiptDTO> pageGoodsReceipt(PageQuery pageQuery) {
        return PageResult.build(goodsReceiptService.pageGoodsReceipt(pageQuery));
    }

    @GetMapping("download/{id}")
    public void downloadFile(@PathVariable Long id, HttpServletResponse response) {
        goodsReceiptService.downloadDeliverySheet(id, response);
    }

    @GetMapping("erp/search")
    public R<GoodsReceiptDTO> searchErp(String docNo, String docTypeNo) {
        return R.ok(goodsReceiptService.searchErp(docTypeNo, docNo));
    }

    @GetMapping("/detail/{id}")
    public R<List<GoodsReceiptDetailDTO>> getGoodsReceiptDetail(@PathVariable Long id) {
        return R.ok(goodsReceiptService.getGoodsReceiptDetail(id));
    }

    @GetMapping("pageFailFile")
    public PageResult<FailedFileDTO> pageFailFile(PageQuery pageQuery) {
        return PageResult.build(failedFileService.pageFailFile(pageQuery));
    }

    @GetMapping("downloadFailedFile/{id}")
    public void downloadFailedFile(@PathVariable Long id, HttpServletResponse response) {
        failedFileService.downloadFailedFile(id, response);
    }
}
