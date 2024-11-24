package com.trilight.ocr.model.pojo.sales;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Builder
@TableName("contract")
@Data
public class ContractDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String contractNum;

    private String docNo;

    private String docTypeNo;
}
