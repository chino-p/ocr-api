package com.trilight.ocr.model.dto.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.trilight.ocr.model.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsReceiptDTO extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String docNo;

    private String docTypeNo;

    private String supplierNo;

    private String ossFileName;

    private String fileName;

    private String deliveryNum;

    private String createBy;

    private String createUser;

    private String updateBy;

    private String updateUser;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
