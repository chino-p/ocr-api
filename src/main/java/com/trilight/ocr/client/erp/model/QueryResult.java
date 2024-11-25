package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class QueryResult<T> {
    @JsonProperty("cnt")
    private int cnt;

    @JsonProperty("rows")
    private List<T> rows;

    @JsonProperty("success")
    private List<Success<T>> successList;
}