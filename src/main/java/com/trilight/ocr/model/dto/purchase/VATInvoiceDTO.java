package com.trilight.ocr.model.dto.purchase;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.trilight.ocr.model.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ColumnWidth(value = 20)
@HeadFontStyle(fontHeightInPoints = 8, fontName = "DejaVu Sans")
@ContentFontStyle(fontHeightInPoints = 8, fontName = "DejaVu Sans")
@EqualsAndHashCode(callSuper = true)
@Data
public class VATInvoiceDTO extends BaseEntity {

    private Long vatInvoiceId;

    /**
     * 购买方名称
     */
    @ExcelProperty("购买方名称")
    private String purchaserName;

    /**
     * 销售方名称
     */
    @ExcelProperty("销售方名称")
    private String sellerName;

    /**
     * 发票号码
     */
    @ExcelProperty("发票号码")
    private String invoiceNum;

    /**
     * 开票日期
     */
    @ExcelProperty("开票日期")
    private LocalDate invoiceDate;

    /**
     * 价税合计
     */
    @ExcelProperty("价税合计")
    private BigDecimal totalAmountIncludeTax;

    /**
     * 合计税额
     */
    @ExcelProperty("合计税额")
    private BigDecimal totalTax;

    /**
     * 不含税合计金额
     */
    @ExcelProperty("不含税合计金额")
    private BigDecimal totalAmount;

    @ExcelIgnore
    private List<CommodityDTO> commodityList;
}
