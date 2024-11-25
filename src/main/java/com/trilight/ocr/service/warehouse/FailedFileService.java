package com.trilight.ocr.service.warehouse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.model.dto.warehouse.FailedFileDTO;
import com.trilight.ocr.model.pojo.warehouse.FailedFileDO;

public interface FailedFileService extends IService<FailedFileDO> {
    IPage<FailedFileDTO> pageFailFile(PageQuery pageQuery);
}