package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataKey {

    @JsonProperty("doc_type_no")
    private  String docTypeNo;

    @JsonProperty("doc_no")
    private String docNo;
}
