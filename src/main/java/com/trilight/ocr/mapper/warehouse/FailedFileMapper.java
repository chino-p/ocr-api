package com.trilight.ocr.mapper.warehouse;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trilight.ocr.model.pojo.warehouse.FailedFileDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FailedFileMapper extends BaseMapper<FailedFileDO> {
}