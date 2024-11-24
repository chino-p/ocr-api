package com.trilight.ocr.model.dto.sales;

import lombok.Data;

@Data
public class SaleOrderDTO {

    private String docTypeNo;

    private String docNo;

    private String orderDate;

    private String customerNo;

    private String approveStatus;

}
