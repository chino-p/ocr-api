package com.trilight.orc.model.dto.purchase;

import com.trilight.orc.model.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityDTO extends BaseEntity {

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
