package com.trilight.ocr.utils;

import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class FileProcessUtil {

    public static String processFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName == null || (!fileName.endsWith(".pdf") && !fileName.endsWith(".jpg"))) {
                throw new BizException(BizCodeEnum.SUPPORT_PDF_ONLY);
            }
            return processPdfToBase64(file.getInputStream());
        } catch (IOException e) {
            throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
        }
    }

    public static String processPdfToBase64(InputStream pdfInputStream) throws IOException {
        byte[] pdfBytes = pdfInputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(pdfBytes);
    }
}
