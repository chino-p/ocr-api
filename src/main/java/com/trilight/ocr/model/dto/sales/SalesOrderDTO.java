package com.trilight.ocr.model.dto.sales;

import lombok.Data;

@Data
public class SalesOrderDTO {

    private String docTypeNo;

    private String docNo;

    private String orderDate;

    private String companyNo;

    private String contractNum;

    private String approveStatus;

}
