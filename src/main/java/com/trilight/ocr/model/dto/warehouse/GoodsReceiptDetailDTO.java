package com.trilight.ocr.model.dto.warehouse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsReceiptDetailDTO {

    private String itemNo;

    private String itemName;

    private String itemSpec;

    private BigDecimal arrivalQty;
}
