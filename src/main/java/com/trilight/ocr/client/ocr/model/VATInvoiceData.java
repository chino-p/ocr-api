package com.trilight.ocr.client.ocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VATInvoiceData {

    @JsonProperty("log_id")
    private String logId;

    @JsonProperty("words_result_num")
    private int wordsResultNum;

    @JsonProperty("words_result")
    private WordsResult wordsResult;
}
