package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Success {

    @JsonProperty("sales_order_data")
    private List<SalesOrder> salesOrderList;
}
