package com.trilight.ocr.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("delivery_header")
public class DeliveryOrderDO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 供方编码
     */
    private String supplierCode;

    /**
     * 出货时间
     */
    private LocalDate deliveryDate;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 送货地址
     */
    private String shippingAddress;

    /**
     * 联系电话
     */
    private String contactNumber;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
