package com.trilight.ocr.model.pojo.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trilight.ocr.model.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@TableName("good_receipt")
@Data
public class GoodsReceiptDO extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String docNo;

    private String docTypeNo;

    private String supplierNo;

    private String fileName;

    private String ossFileName;

    private String deliveryNum;

    private String createBy;

    private String createUser;

    private String updateBy;

    private String updateUser;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
