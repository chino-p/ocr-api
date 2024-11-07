package com.trilight.ocr.utils;

import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FileProcessUtil {

    /**
     * Convert PDF file to Base64 string
     *
     * @param files files from the request
     * @return Base64 string list
     */
    public static List<String> processFile(MultipartFile[] files) {
        try {
            List<String> pdfContents = new ArrayList<>();

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName == null || !fileName.endsWith(".pdf")) {
                    throw new BizException(BizCodeEnum.SUPPORT_PDF_ONLY);
                }
                pdfContents.add(processPdfToBase64(file.getInputStream()));
            }

            return pdfContents;
        } catch (IOException e) {
            throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
        }
    }

    private static String processPdfToBase64(InputStream pdfInputStream) throws IOException {
        byte[] pdfBytes = pdfInputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(pdfBytes);
    }
}
