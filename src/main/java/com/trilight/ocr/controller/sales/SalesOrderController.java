package com.trilight.ocr.controller.sales;

import cn.hutool.core.bean.BeanUtil;
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
import com.trilight.ocr.model.dto.sales.SalesOrderDTO;
import com.trilight.ocr.model.pojo.sales.ContractDO;
import com.trilight.ocr.service.purchase.ContractService;
import com.trilight.ocr.utils.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
    public PageResult<SalesOrderDTO> pageSalesOrder(PageQuery pageQuery, SalesOrderDTO salesOrderDTO) throws
            JsonProcessingException {
        ErpRequest<RequestParameter> erpRequest1 = generateRequest("0003", "2201");
        ErpRequest<RequestParameter> erpRequest2 = generateRequest("0001", "2201");

        ErpRequest<ResultParameter<SalesOrder>> resp1 = ErpClient.request(erpRequest1,
                "yf.oapi.sales.order.data.query.get", "0001", SalesOrder.class);
        List<SalesOrder> salesOrderList1 = resp1.getStdData().getParameter().getQueryResult().getRows();
        List<SalesOrderDTO> salesOrderDTOList1 = BeanUtil.copyToList(salesOrderList1, SalesOrderDTO.class);
        for (SalesOrderDTO orderDTO : salesOrderDTOList1) {
            orderDTO.setCompanyNo("0001");
        }
        ErpRequest<ResultParameter<SalesOrder>> resp2 = ErpClient.request(erpRequest2,
                "yf.oapi.sales.order.data.query.get", "0003", SalesOrder.class);
        List<SalesOrder> salesOrderList2 = resp2.getStdData().getParameter().getQueryResult().getRows();
        List<SalesOrderDTO> salesOrderDTOList2 = BeanUtil.copyToList(salesOrderList2, SalesOrderDTO.class);
        for (SalesOrderDTO orderDTO : salesOrderDTOList2) {
            orderDTO.setCompanyNo("0003");
        }

        salesOrderDTOList1.addAll(salesOrderDTOList2);
        return PageResult.build(salesOrderDTOList1);
    }

    @NotNull
    private static ErpRequest<RequestParameter> generateRequest(String customerNo, String docTypeNo) {
        StdData<RequestParameter> stdData = new StdData<>();
        ErpRequest<RequestParameter> erpRequest = new ErpRequest<>();
        erpRequest.setStdData(stdData);
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setPageNo(1);
        requestParameter.setPageSize(100000);
        Conditions condition = new Conditions();
        Field field1 = new Field();
        field1.setFieldName("customer_no");
        field1.setValue(customerNo);
        field1.setOperator("=");
        // Field field2 = new Field();
        // field2.setFieldName("doc_type_no");
        // field2.setValue(docTypeNo);
        // field2.setOperator("=");
        Field field3 = new Field();
        field3.setFieldName("remarks_1");
        field3.setValue("1");
        field3.setOperator("=");
        List<Field> fieldList = new ArrayList<>();
        fieldList.add(field1);
        // fieldList.add(field2);
        condition.setFields(fieldList);
        condition.setOperator("AND");
        stdData.setParameter(requestParameter);
        requestParameter.setConditions(condition);
        return erpRequest;
    }

    @GetMapping("/downloadContract")
    public void downloadContract(HttpServletResponse response, String docTypeNo, String docNo, String companyNo) {
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
                    "yf.oapi.sales.order.data.read.get", companyNo, SalesOrder.class);
            List<Success<SalesOrder>> successList = request.getStdData().getParameter().getQueryResult()
                    .getSuccessList();
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
            String contractPrefix = "";
            if ("0001".equals(companyNo)) {
                contractPrefix = "JHXS";
            } else {
                contractPrefix = "ZJXS";
            }
            if (contractDO != null) {
                contractNum = contractDO.getContractNum();
            } else {
                ContractDO latestContract = contractService.getOne(new LambdaQueryWrapper<ContractDO>()
                        .like(ContractDO::getContractNum, docDate)
                        .orderByDesc(ContractDO::getId)
                        .last("LIMIT 1"));
                if (latestContract == null) {
                    contractNum = contractPrefix + docDate + "001";
                } else {
                    contractNum = contractPrefix + (Long.parseLong(latestContract.getContractNum().substring(4)) + 1);
                }
                contractService.save(
                        ContractDO.builder().companyNo(companyNo).contractNum(contractNum).docNo(docNo).docTypeNo(docTypeNo).build());
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

            String htmlContent;
            if ("0001".equals(companyNo)) {
                for (ProductDTO productDTO : productList) {
                    productDTO.setName(productDTO.getName().replaceFirst("光模块", "") + " 中性模块");
                }
                htmlContent = thymeleafRenderer.renderTemplate("contract.html", model);
            } else {
                htmlContent = thymeleafRenderer.renderTemplate("contract2.html", model);
            }

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
