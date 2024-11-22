package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErpRequest<T> {

    @JsonProperty("std_data")
    private StdData<T> stdData;
}
