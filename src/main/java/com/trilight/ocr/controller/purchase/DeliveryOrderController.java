package com.trilight.ocr.controller.purchase;

import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.service.purchase.DeliveryOrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@RestController
@Validated
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @PostMapping("/parse_code")
    public R<Void> parseCode(@RequestParam("files") @NotNull(message = "文件不能为空") MultipartFile[] files) {
        deliveryOrderService.parseCode(files);
        return R.ok();
    }

    @GetMapping("page")
    public PageResult<DeliveryOrderDTO> pageDeliveryOrder(PageQuery pageQuery, DeliveryOrderDTO deliveryOrderDTO) {
        return deliveryOrderService.pageDeliveryOrder(pageQuery, deliveryOrderDTO);
    }

    @GetMapping("download/{id}")
    public void downloadFile(@PathVariable("id") Long id, HttpServletResponse response) {
        deliveryOrderService.downloadConfirmFile(id, response);
    }
}
