package com.trilight.ocr.controller.sales;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.trilight.ocr.client.erp.ErpClient;
import com.trilight.ocr.client.erp.model.*;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.config.ThymeleafRenderer;
import com.trilight.ocr.model.dto.sales.ProductDTO;
import com.trilight.ocr.model.dto.sales.SaleOrderDTO;
import com.trilight.ocr.model.pojo.sales.ContractDO;
import com.trilight.ocr.service.purchase.ContractService;
import com.trilight.ocr.utils.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/salesOrder")
@RequiredArgsConstructor
@RestController
public class SalesOrderController {

    private final ThymeleafRenderer thymeleafRenderer;
    private final ResourceLoader resourceLoader;
    private final ContractService contractService;

    @GetMapping("page")
    public PageResult<SalesOrder> pageSalesOrder(PageQuery pageQuery, SaleOrderDTO saleOrderDTO) throws
            JsonProcessingException {
        StdData<RequestParameter> stdData = new StdData<>();
        ErpRequest<RequestParameter> erpRequest = new ErpRequest<>();
        erpRequest.setStdData(stdData);
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setPageNo(1);
        requestParameter.setPageSize(100000);
        Conditions condition = new Conditions();
        Field field1 = new Field();
        field1.setFieldName("customer_no");
        field1.setValue("0003");
        field1.setOperator("=");
        Field field2 = new Field();
        field2.setFieldName("doc_type_no");
        field2.setValue("2201");
        field2.setOperator("=");
        Field field3 = new Field();
        field3.setFieldName("remarks_1");
        field3.setValue("1");
        field3.setOperator("=");
        List<Field> fieldList = new ArrayList<>();
        fieldList.add(field1);
        fieldList.add(field2);
        condition.setFields(fieldList);
        condition.setOperator("AND");
        stdData.setParameter(requestParameter);
        requestParameter.setConditions(condition);

        ErpRequest<ResultParameter<SalesOrder>> request = ErpClient.request(erpRequest,
                "yf.oapi.sales.order.data.query.get", SalesOrder.class);
        return PageResult.build(request.getStdData().getParameter().getQueryResult().getRows());
    }

    @GetMapping("/downloadContract")
    public void downloadInvoice(HttpServletResponse response, String docTypeNo, String docNo) {
        try {
            StdData<ReadQueryParameter> stdData = new StdData<>();
            ErpRequest<ReadQueryParameter> erpRequest = new ErpRequest<>();
            erpRequest.setStdData(stdData);
            ReadQueryParameter requestParameter = new ReadQueryParameter();
            List<DataKey> dataKeyList = new ArrayList<>();
            DataKey dataKey = new DataKey();
            dataKey.setDocTypeNo(docTypeNo);
            dataKey.setDocNo(docNo);
            dataKeyList.add(dataKey);
            requestParameter.setDataKeyList(dataKeyList);
            stdData.setParameter(requestParameter);

            ErpRequest<ResultParameter<SalesOrder>> request = ErpClient.request(erpRequest,
                    "yf.oapi.sales.order.data.read.get", SalesOrder.class);
            List<Success<SalesOrder>> successList = request.getStdData().getParameter().getQueryResult().getSuccessList();
            if (successList.isEmpty()) {
                throw new RuntimeException("Failed to download contract");
            }
            SalesOrder salesOrder = successList.get(0).getSalesOrderList().get(0);
            List<SalesOrderDetail> salesOrderDetailList = salesOrder.getSalesOrderDetailList();
            String docDate = salesOrder.getDocDate();
            ContractDO contractDO = contractService.getOne(
                    new QueryWrapper<ContractDO>().eq("doc_no", salesOrder.getDocNo())
                            .eq("doc_type_no", salesOrder.getDocTypeNo()));

            ModelMap model = new ModelMap();
            String contractNum = "";
            if (contractDO != null) {
                contractNum = contractDO.getContractNum();
            } else {
                ContractDO latestContract = contractService.getOne(new LambdaQueryWrapper<ContractDO>()
                        .like(ContractDO::getContractNum, docDate)
                        .orderByDesc(ContractDO::getId)
                        .last("LIMIT 1"));
                if (latestContract == null) {
                    contractNum = "JHXS" + docDate + "001";
                } else {
                    contractNum = "JHXS" + (Long.parseLong(latestContract.getContractNum().substring(4)) + 1);
                }
                contractService.save(
                        ContractDO.builder().contractNum(contractNum).docNo(docNo).docTypeNo(docTypeNo).build());
            }

            model.addAttribute("currentDate", LocalDate.parse(docDate,
                    DateTimeFormatter.ofPattern("yyyyMMdd")).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));

            model.addAttribute("contractNo", contractNum);
            List<ProductDTO> productList = salesOrderDetailList.stream()
                    .map(salesOrderDetail -> new ProductDTO(salesOrderDetail.getItemName(),
                            salesOrderDetail.getItemSpec(),
                            salesOrderDetail.getOrderQty(),
                            salesOrderDetail.getPrice(), salesOrderDetail.getAmount(),
                            salesOrderDetail.getPlanDeliveryDate(),
                            salesOrderDetail.getRemarks())).toList();

            BigDecimal totalAmount = productList.stream().map(ProductDTO::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            model.addAttribute("productList", productList);
            model.addAttribute("totalAmount", totalAmount);
            model.addAttribute("totalAmountCn", Convert.digitToChinese(totalAmount));

            String htmlContent = thymeleafRenderer.renderTemplate("contract.html", model);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfGenerator pdfGenerator = new PdfGenerator(resourceLoader);
            pdfGenerator.generatePdf(htmlContent, outputStream);
            byte[] pdfBytes = outputStream.toByteArray();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + contractNum + ".pdf");

            Path outputPath = Path.of("output/test_contract.pdf");
            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, pdfBytes);

            try (OutputStream os = response.getOutputStream()) {
                os.write(pdfBytes);
                os.flush();
            }
        } catch (
                Exception e) {
            throw new RuntimeException("Failed to download invoice", e);
        }
    }
}
