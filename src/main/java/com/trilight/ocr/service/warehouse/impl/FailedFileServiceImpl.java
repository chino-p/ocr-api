package com.trilight.ocr.service.warehouse.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.mapper.warehouse.FailedFileMapper;
import com.trilight.ocr.model.dto.warehouse.FailedFileDTO;
import com.trilight.ocr.model.pojo.warehouse.FailedFileDO;
import com.trilight.ocr.service.warehouse.FailedFileService;
import org.springframework.stereotype.Service;

@Service
public class FailedFileServiceImpl extends ServiceImpl<FailedFileMapper, FailedFileDO> implements FailedFileService {

    @Override
    public IPage<FailedFileDTO> pageFailFile(PageQuery pageQuery) {
        Page<FailedFileDO> page = page(pageQuery.build());
        Page<FailedFileDTO> failedFile = new Page<>();
        failedFile.setSize(page.getSize());
        failedFile.setTotal(page.getTotal());
        failedFile.setRecords(BeanUtil.copyToList(page.getRecords(), FailedFileDTO.class));
        return failedFile;
    }
}