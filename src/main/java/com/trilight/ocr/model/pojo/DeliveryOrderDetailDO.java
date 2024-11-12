package com.trilight.ocr.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 送货单单身
 */
@Data
@TableName("delivery_detail")
public class DeliveryOrderDetailDO implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 送货单单头id
     */
    private Integer headerId;

    /**
     * 客户品号
     */
    private String customerProductNum;

    /**
     * 品名
     */
    private String productName;

    /**
     * 采购单号
     */
    private String po;

    /**
     * 规格型号
     */
    private String specification;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 批次
     */
    private String batchNum;

    /**
     * 生产日期
     */
    private LocalDate produceDate;

    /**
     * 箱数
     */
    private BigDecimal boxAmount;

    /**
     * 快递单号
     */
    private String trackingNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}