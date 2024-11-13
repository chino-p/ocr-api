package com.trilight.ocr.model.pojo;

import lombok.Data;

@Data
public class BaseEntity {

    private String createBy;

    private String createUser;

    private String updateBy;

    private String updateUser;

    private String createTime;

    private String updateTime;
}
