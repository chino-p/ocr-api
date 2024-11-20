package com.trilight.ocr.model.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private String name; // 产品名称
    private String specification; // 规格
    private int quantity; // 数量
    private BigDecimal unitPrice; // 单价
    private BigDecimal totalPrice; // 金额
    private String deliveryDate; // 交货时间
    private String remark;
}
