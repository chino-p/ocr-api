package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReadResultParameter {

    @JsonProperty("result")
    private ReadResult result;
}
