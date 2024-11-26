package com.trilight.ocr.model.dto.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trilight.ocr.model.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsReceiptDTO extends BaseEntity {

    private Integer id;

    private String docNo;

    private String docTypeNo;

    private String supplierNo;

    private String ossFileName;

    private String fileName;

    private String deliveryNum;

    @JsonProperty("goodsReceiptDetail")
    private List<GoodsReceiptDetailDTO> goodsReceiptDetailDTOList;
}
