package com.trilight.ocr.model.dto.purchase;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 送货单确认文件
 */
@Data
public class DeliveryConfirmFileDTO {

    private Long id;

    private Long deliveryId;

    private String fileName;

    private String ossFileName;

    private LocalDateTime createTime;
}
