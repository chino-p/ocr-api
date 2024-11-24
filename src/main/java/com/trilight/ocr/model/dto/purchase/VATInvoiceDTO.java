package com.trilight.ocr.model.dto.purchase;

import com.trilight.ocr.model.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class VATInvoiceDTO extends BaseEntity {

    private Long vatInvoiceId;

    /**
     * 购买方名称
     */
    private String purchaserName;

    /**
     * 销售方名称
     */
    private String sellerName;

    private String supplierNo;

    private String ossFileName;

    /**
     * 发票号码
     */
    private String invoiceNum;

    /**
     * 开票日期
     */
    private LocalDate invoiceDate;

    /**
     * 价税合计
     */
    private BigDecimal totalAmountIncludeTax;

    /**
     * 合计税额
     */
    private BigDecimal totalTax;

    /**
     * 不含税合计金额
     */
    private BigDecimal totalAmount;

    private List<CommodityDTO> commodityList;
}
