package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestParameter {

    @JsonProperty("page_no")
    private int pageNo;

    @JsonProperty("conditions")
    private Conditions conditions;

    @JsonProperty("use_has_next")
    private boolean useHasNext;

    @JsonProperty("page_size")
    private int pageSize;
}