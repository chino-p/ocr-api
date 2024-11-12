package com.trilight.ocr.model.dto.purchase;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 送货单单身
 */
@Data
public class DeliveryOrderDetailDTO {

    private Integer id;

    /**
     * 送货单单头id
     */
    private Integer headerId;

    /**
     * 客户品号
     */
    private String customerProductNum;

    /**
     * 品名
     */
    private String productName;

    /**
     * 采购单号
     */
    private String po;

    /**
     * 规格型号
     */
    private String specification;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 批次
     */
    private String batchNum;

    /**
     * 生产日期
     */
    private LocalDate produceDate;

    /**
     * 箱数
     */
    private BigDecimal boxAmount;

    /**
     * 快递单号
     */
    private String trackingNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}