package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Execution {
    @JsonProperty("code")
    private String code;

    @JsonProperty("sql_code")
    private String sqlCode;

    @JsonProperty("description")
    private String description;
}