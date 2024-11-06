package com.trilight.orc.controller.purchase;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RequestMapping("/vat_invoice")
@RequiredArgsConstructor
@RestController
@Validated
public class VATInvoiceController {

    private final VATInvoiceService vatInvoiceService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") @NotNull(message = "文件不能为空") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file name");
            }

            List<String> pdfContents;
            if (fileName.endsWith(".pdf")) {
                // 处理 PDF 文件
                pdfContents = List.of(processPdf(file.getInputStream()));
            } else if (fileName.endsWith(".zip")) {
                // 处理 ZIP 文件中的 PDF
                pdfContents = processZipFile(file);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported file type");
            }

            // 返回解析结果
            return ResponseEntity.ok(pdfContents);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File processing error", e);
        }
    }

    // 处理 PDF 文件（将 PDF 转换为 Base64 或解析为文本）
    private String processPdf(InputStream pdfInputStream) throws IOException {
        // 这里可以添加具体的 PDF 解析逻辑
        // 暂时用简单的例子来模拟：将 PDF 文件内容转换为 Base64
        byte[] pdfBytes = pdfInputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(pdfBytes);
    }

    // 处理 ZIP 文件，遍历其中的 PDF 文件并解析
    private List<String> processZipFile(MultipartFile zipFile) throws IOException {
        List<String> pdfContents = new ArrayList<>();

        try (ZipInputStream zipIn = new ZipInputStream(zipFile.getInputStream())) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String entryName = entry.getName();
                if (!entry.isDirectory() && entryName.endsWith(".pdf")) {
                    // 读取每个 PDF 文件的内容并解析
                    pdfContents.add(processPdf(zipIn));
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
        return pdfContents;
    }
}
