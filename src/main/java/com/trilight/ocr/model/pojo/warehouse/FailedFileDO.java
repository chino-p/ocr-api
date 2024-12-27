package com.trilight.ocr.model.pojo.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("failed_file")
public class FailedFileDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String fileName;

    private String ossFileName;

    private String reason;

    private Integer status;

    private String remark;
}