package com.trilight.ocr.service.warehouse.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.mapper.warehouse.FailedFileMapper;
import com.trilight.ocr.model.pojo.warehouse.FailedFileDO;
import com.trilight.ocr.service.warehouse.FailedFileService;
import org.springframework.stereotype.Service;

@Service
public class FailedFileServiceImpl extends ServiceImpl<FailedFileMapper, FailedFileDO> implements FailedFileService {
}