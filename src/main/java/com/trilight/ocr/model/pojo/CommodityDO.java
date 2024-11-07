package com.trilight.ocr.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommodityDO {

    @TableId
    private Long commodityId;

    private Long vatInvoiceId;

    /**
     * 项目名称
     */
    private String commodityName;

    /**
     * 规格型号
     */
    private String commodityType;

    /**
     * 单位
     */
    private String commodityUnit;

    /**
     * 单价
     */
    private BigDecimal commodityPrice;

    /**
     * 数量
     */
    private BigDecimal commodityNum;

    /**
     * 金额
     */
    private BigDecimal commodityAmount;

    /**
     * 税率
     */
    private BigDecimal commodityTaxRate;

    /**
     * 税额
     */
    private BigDecimal commodityTax;
}
