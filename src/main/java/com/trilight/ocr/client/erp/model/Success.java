package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Success<T> {

    @JsonProperty("sales_order_data")
    private List<T> salesOrderList;

    @JsonProperty("purchase_receipt_data")
    private List<T> purchaseReceiptData;
}
