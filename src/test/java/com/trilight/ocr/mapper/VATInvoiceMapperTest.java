package com.trilight.ocr.mapper;

import com.trilight.ocr.config.ThymeleafRenderer;
import com.trilight.ocr.utils.PdfGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.ModelMap;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
 class VATInvoiceMapperTest {

    // @Autowired
    // BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void testInsert() {
        // System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    @Autowired
    private ThymeleafRenderer thymeleafRenderer;

    @Test
    void testGeneratePdf() throws Exception {
        // Step 1: Prepare Thymeleaf model
        ModelMap model = new ModelMap();
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));

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
