package com.trilight.ocr.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VATInvoiceDO {

    @TableId
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
     * 发票号码
     */
    private String invoiceNum;

    /**
     * 开票日期
     */
    private LocalDateTime invoiceDate;

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

    private List<CommodityDO> commodityList;
}
