package com.trilight.ocr.model.pojo.purchase;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trilight.ocr.model.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@TableName("vat_invoice")
@EqualsAndHashCode(callSuper = true)
@Data
public class VATInvoiceDO extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long vatInvoiceId;

    /**
     * 购买方名称
     */
    private String purchaserName;

    /**
     * 销售方名称
     */
    private String sellerName;

    /**
     * 供应商编码
     */
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

    @TableField(exist = false)
    private List<CommodityDO> commodityList;
}
