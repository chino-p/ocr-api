package com.trilight.ocr.controller.purchase;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.dto.purchase.VATInvoiceExcelDTO;
import com.trilight.ocr.model.pojo.purchase.CommodityDO;
import com.trilight.ocr.model.pojo.purchase.VATInvoiceDO;
import com.trilight.ocr.service.purchase.CommodityService;
import com.trilight.ocr.service.purchase.VATInvoiceService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/vat_invoice")
@RequiredArgsConstructor
@RestController
@Validated
public class VATInvoiceController {

    private final VATInvoiceService vatInvoiceService;
    private final CommodityService commodityService;

    @PostMapping("/upload")
    public R<Void> uploadFiles(@RequestParam("files") @NotNull(message = "文件不能为空") MultipartFile[] files) {
        return vatInvoiceService.uploadInvoiceFiles(files);
    }

    @GetMapping("page")
    public PageResult<VATInvoiceDTO> pageVATInvoice(PageQuery pageQuery, VATInvoiceDTO vatInvoiceDTO,
                                                    @RequestParam(required = false) LocalDate startDate,
                                                    @RequestParam(required = false) LocalDate endDate,
                                                    @RequestParam(required = false) LocalDateTime createStartTime,
                                                    @RequestParam(required = false) LocalDateTime createEndTime) {
        return vatInvoiceService.pageVATInvoice(pageQuery, vatInvoiceDTO, startDate, endDate, createStartTime,
                createEndTime);
    }

    @PutMapping("/{vatInvoiceId}")
    public R<Void> updateVATInvoice(@PathVariable Long vatInvoiceId, @RequestBody VATInvoiceDTO vatInvoiceDTO) {
        vatInvoiceService.updateVATInvoice(vatInvoiceId, vatInvoiceDTO);
        return R.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/{vatInvoiceId}")
    public R<Void> deleteVATInvoice(@PathVariable Long vatInvoiceId) {
        vatInvoiceService.removeById(vatInvoiceId);
        commodityService.remove(new QueryWrapper<CommodityDO>().eq("vat_invoice_id", vatInvoiceId));
        return R.ok();
    }

    @GetMapping("/export")
    public void exportVATInvoice(
            @RequestParam(required = false) String invoiceNum,
            @RequestParam(required = false) String sellerName,
            @RequestParam(required = false) String purchaserName,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) LocalDateTime createStartTime,
            @RequestParam(required = false) LocalDateTime createEndTime,
                    HttpServletResponse response) throws IOException {

        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("采购发票", StandardCharsets.UTF_8).replace("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        List<VATInvoiceDO> vatInvoiceDOList = vatInvoiceService.list(new QueryWrapper<VATInvoiceDO>()
                .like(!ObjectUtils.isEmpty(invoiceNum), "invoice_num", invoiceNum)
                .like(!ObjectUtils.isEmpty(sellerName), "seller_name", sellerName)
                .like(!ObjectUtils.isEmpty(purchaserName), "purchaser_name", purchaserName)
                .ge(startDate != null, "invoice_date", startDate)
                .le(endDate != null, "invoice_date", endDate)
                .ge(createStartTime != null, "create_time", createStartTime)
                .le(createEndTime != null, "create_time", createEndTime)
        );

        List<VATInvoiceExcelDTO> vatInvoiceDTOList = BeanUtil.copyToList(vatInvoiceDOList, VATInvoiceExcelDTO.class);

        EasyExcelFactory.write(response.getOutputStream(), VATInvoiceExcelDTO.class).sheet("采购发票")
                .doWrite(vatInvoiceDTOList);
    }
}