package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultParameter {
    @JsonProperty("total_result")
    private int totalResult;

    @JsonProperty("has_next")
    private boolean hasNext;

    @JsonProperty("result")
    private Result result;
}