package com.trilight.ocr.controller.purchase;

import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.service.purchase.DeliveryOrderService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/delivery")
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
}
