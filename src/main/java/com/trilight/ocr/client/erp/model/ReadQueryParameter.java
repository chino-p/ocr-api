package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReadQueryParameter {

    @JsonProperty("datakeys")
    private List<DataKey> dataKeyList;
}
