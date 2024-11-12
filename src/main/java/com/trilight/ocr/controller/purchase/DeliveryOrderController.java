package com.trilight.ocr.controller.purchase;

import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.service.purchase.DeliveryOrderService;
import com.trilight.ocr.utils.FileProcessUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/delivery")
@RequiredArgsConstructor
@RestController
@Validated
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @PostMapping("/parse_code")
    public R<Void> parseCode(@RequestParam("files") @NotNull(message = "文件不能为空") MultipartFile[] files) {
        try {
            List<String> base64Contents = FileProcessUtil.processFile(files);
            BaiduOcrClient.parseCode(base64Contents.get(0));
        } catch (Exception e) {
            throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
        }

        return R.ok();
    }

    @GetMapping("page")
    public PageResult<DeliveryOrderDTO> pageDeliveryOrder(PageQuery pageQuery, DeliveryOrderDTO deliveryOrderDTO) {
        return deliveryOrderService.pageDeliveryOrder(pageQuery, deliveryOrderDTO);
    }
}
