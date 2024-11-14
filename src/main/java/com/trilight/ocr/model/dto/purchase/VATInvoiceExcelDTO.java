package com.trilight.ocr.model.dto.purchase;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ColumnWidth(value = 20)
@HeadFontStyle(fontHeightInPoints = 10, fontName = "DejaVu Sans")
@ContentFontStyle(fontHeightInPoints = 10, fontName = "DejaVu Sans")
@Data
public class VATInvoiceExcelDTO {

    @ExcelIgnore
    private Long vatInvoiceId;

    @ExcelProperty("开票日期")
    private LocalDate invoiceDate;

    @ExcelProperty("发票号码")
    private String invoiceNum;

    /**
     * 销售方名称
     */
    @ExcelProperty("供方名称")
    private String sellerName;

    /**
     * 价税合计
     */
    @ExcelProperty("发票金额")
    private BigDecimal totalAmountIncludeTax;

    /**
     * 不含税合计金额
     */
    @ExcelProperty("不含税金额")
    private BigDecimal totalAmount;

    /**
     * 合计税额
     */
    @ExcelProperty("税额")
    private BigDecimal totalTax;

    /**
     * 购买方名称
     */
    @ExcelProperty("购买方名称")
    private String purchaserName;

    @ExcelProperty("上传日期")
    private LocalDateTime createTime;

    @ExcelIgnore
    private List<CommodityDTO> commodityList;
}
