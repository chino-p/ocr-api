package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Result {
    @JsonProperty("cnt")
    private int cnt;

    @JsonProperty("rows")
    private List<SalesOrder> rows;
}