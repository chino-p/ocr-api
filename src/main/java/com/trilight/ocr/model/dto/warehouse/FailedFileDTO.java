package com.trilight.ocr.model.dto.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class FailedFileDTO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String fileName;

    private String ossFileName;

    private String reason;

    private Integer status;
}