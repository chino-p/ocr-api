package com.trilight.ocr.client.ocr.model;

import lombok.Data;

import java.util.List;

@Data
public class Code {

    private List<String> text;

    private String type;
}
