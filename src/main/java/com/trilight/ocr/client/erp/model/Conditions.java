package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Conditions {

    @JsonProperty("fields")
    private List<Field> fields;

    @JsonProperty("operator")
    private String operator;
}