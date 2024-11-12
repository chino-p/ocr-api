package com.trilight.ocr.client.ocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CodeResult {

    @JsonProperty("code_results")
    private List<Code> codeResults;

    @JsonProperty("codes_result_num")
    private int codesResultNum;

    @JsonProperty("log_id")
    private long logId;
}
