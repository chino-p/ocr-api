package com.trilight.ocr.service.warehouse.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.client.minio.MinioService;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.mapper.warehouse.FailedFileMapper;
import com.trilight.ocr.model.dto.warehouse.FailedFileDTO;
import com.trilight.ocr.model.pojo.warehouse.FailedFileDO;
import com.trilight.ocr.service.warehouse.FailedFileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Service
public class FailedFileServiceImpl extends ServiceImpl<FailedFileMapper, FailedFileDO> implements FailedFileService {

    private final MinioService minioService;

    @Override
    public IPage<FailedFileDTO> pageFailFile(PageQuery pageQuery) {
        Page<FailedFileDO> page = page(pageQuery.build());
        Page<FailedFileDTO> failedFile = new Page<>();
        failedFile.setSize(page.getSize());
        failedFile.setTotal(page.getTotal());
        failedFile.setRecords(BeanUtil.copyToList(page.getRecords(), FailedFileDTO.class));
        return failedFile;
    }

    @Override
    public void downloadFailedFile(Long id, HttpServletResponse response) {
        FailedFileDO failedFileDO = getById(id);
        try {
            if (failedFileDO == null || failedFileDO.getOssFileName() == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invoice not found or file missing.");
                return;
            }

            response.setContentType("image/jpeg");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(failedFileDO.getFileName(), StandardCharsets.UTF_8));

            try (InputStream inputStream = minioService.downloadFile(failedFileDO.getOssFileName());
                 OutputStream outputStream = response.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            }

        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Failed to download files: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}