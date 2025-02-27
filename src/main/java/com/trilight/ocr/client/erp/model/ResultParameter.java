package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResultParameter<T> {
    @JsonProperty("total_result")
    private int totalResult;

    @JsonProperty("has_next")
    private boolean hasNext;

    @JsonProperty("result")
    private QueryResult<T> queryResult;
}