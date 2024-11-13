package com.trilight.ocr.controller.purchase;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.pojo.VATInvoiceDO;
import com.trilight.ocr.service.purchase.VATInvoiceService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/vat_invoice")
@RequiredArgsConstructor
@RestController
@Validated
public class VATInvoiceController {

    private final VATInvoiceService vatInvoiceService;

    @PostMapping("/upload")
    public R<Void> uploadFiles(@RequestParam("files") @NotNull(message = "文件不能为空") MultipartFile[] files) {
        return vatInvoiceService.uploadInvoiceFiles(files);
    }

    @GetMapping("page")
    public PageResult<VATInvoiceDTO> pageVATInvoice(PageQuery pageQuery, VATInvoiceDTO vatInvoiceDTO) {
        return vatInvoiceService.pageVATInvoice(pageQuery, vatInvoiceDTO);
    }

    @PutMapping("/{vatInvoiceId}")
    public R<Void> updateVATInvoice(@PathVariable Long vatInvoiceId, @RequestBody VATInvoiceDTO vatInvoiceDTO) {
        vatInvoiceService.updateVATInvoice(vatInvoiceId, vatInvoiceDTO);
        return R.ok();
    }

    @DeleteMapping("/{vatInvoiceId}")
    public R<Void> deleteVATInvoice(@PathVariable Long vatInvoiceId) {
         vatInvoiceService.removeById(vatInvoiceId);
        return R.ok();
    }

    @GetMapping("/export")
    public void exportVATInvoice(
            @RequestParam(required = false) String invoiceNum,
            @RequestParam(required = false) String sellerName,
            @RequestParam(required = false) String purchaserName,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            HttpServletResponse response) throws IOException {

        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("采购发票", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        List<VATInvoiceDO> vatInvoiceDOList = vatInvoiceService.list(new QueryWrapper<VATInvoiceDO>()
                .like(!StringUtils.hasLength(invoiceNum), "invoice_num", invoiceNum)
                .like(!StringUtils.hasLength(sellerName), "seller_name", sellerName)
                .like(!StringUtils.hasLength(purchaserName), "purchaser_name", purchaserName)
                .ge(startDate != null, "invoice_date", startDate)
                .le(endDate != null, "invoice_date", endDate)
        );

        List<VATInvoiceDTO> vatInvoiceDTOList = BeanUtil.copyToList(vatInvoiceDOList, VATInvoiceDTO.class);

        // 使用 EasyExcel 导出数据
        EasyExcel.write(response.getOutputStream(), VATInvoiceDTO.class).sheet("采购发票").doWrite(vatInvoiceDTOList);
    }
}