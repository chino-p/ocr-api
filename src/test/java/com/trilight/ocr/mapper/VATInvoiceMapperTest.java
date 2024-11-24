package com.trilight.ocr.mapper;

import cn.hutool.core.convert.Convert;
import com.trilight.ocr.config.ThymeleafRenderer;
import com.trilight.ocr.model.dto.sales.ProductDTO;
import com.trilight.ocr.utils.PdfGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.ModelMap;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
 class VATInvoiceMapperTest {

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void testInsert() {
    }

    @Autowired
    private ThymeleafRenderer thymeleafRenderer;

    @Test
    void testGeneratePdf() throws Exception {
        ModelMap model = new ModelMap();
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));

        List<ProductDTO> productList = Arrays.asList(
                new ProductDTO("品名1", "规格1", 10, BigDecimal.valueOf(100.00), BigDecimal.valueOf(1000.00), "2024-06-01", "备注1"),
                new ProductDTO("品名2", "规格2", 5,  BigDecimal.valueOf(200.00),  BigDecimal.valueOf(1000.00), "2024-06-05", "备注2")
        );
        BigDecimal totalAmount = productList.stream().map(ProductDTO::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("productList", productList);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalAmountCn", Convert.digitToChinese(totalAmount));
        // Step 2: Render HTML using Thymeleaf
        String renderedHtml = thymeleafRenderer.renderTemplate("contract.html", model);

        // Assert HTML is rendered correctly
        assertThat(renderedHtml).contains("购 销 合 同");

        // Step 3: Generate PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfGenerator pdfGenerator = new PdfGenerator(resourceLoader);
        pdfGenerator.generatePdf(renderedHtml, outputStream);

        // Assert PDF is generated
        byte[] pdfBytes = outputStream.toByteArray();
        assertThat(pdfBytes).isNotEmpty();

        // Optional: Write PDF to a file for verification
        Path outputPath = Path.of("output/test_contract.pdf");
        Files.createDirectories(outputPath.getParent());
        Files.write(outputPath, pdfBytes);

        // Verify file exists
        assertThat(outputPath).exists();
    }
}
