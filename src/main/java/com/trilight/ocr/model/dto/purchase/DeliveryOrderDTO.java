package com.trilight.ocr.model.dto.purchase;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeliveryOrderDTO {

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

    private List<DeliveryOrderDetailDTO> deliveryOrderDetailDTOList;

    private List<DeliveryConfirmFileDTO> deliveryConfirmFileDTOList;
}
