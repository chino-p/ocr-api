package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StdData<T> {

    @JsonProperty("execution")
    private Execution execution;

    @JsonProperty("parameter")
    private T parameter;
}