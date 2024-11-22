package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Field {

    @JsonProperty("value")
    private String value;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("field_name")
    private String fieldName;
}