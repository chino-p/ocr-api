package com.trilight.ocr.utils;

import com.lowagie.text.pdf.BaseFont;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.InputStream;
import java.io.OutputStream;

public class PdfGenerator {

    private final ResourceLoader resourceLoader;

    public PdfGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void generatePdf(String htmlContent, OutputStream outputStream) throws Exception {
        ITextRenderer renderer = new ITextRenderer();

        // 使用 ResourceLoader 加载字体文件
        Resource fontResource = resourceLoader.getResource("classpath:/fonts/SimSun.ttf");

        try (InputStream fontStream = fontResource.getInputStream()) {
            renderer.getFontResolver()
                    .addFont(fontResource.getURI().toString(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }

        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        renderer.getSharedContext().setPrint(true);
        renderer.getSharedContext().setInteractive(false);
        renderer.getSharedContext().getTextRenderer().setSmoothingThreshold(0);

        renderer.createPDF(outputStream);
    }
}