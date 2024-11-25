package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsReceipt {

    @JsonProperty("doc_type_no")
    private String docTypeNo;

    @JsonProperty("doc_no")
    private String docNo;

    @JsonProperty("receipt_date")
    private String receiptDate;

    @JsonProperty("plant_no")
    private String plantNo;

    @JsonProperty("supplier_no")
    private String supplierNo;

    @JsonProperty("supplier_order_no")
    private String supplierOrderNo;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("exchange_rate")
    private double exchangeRate;

    @JsonProperty("invoice_type")
    private String invoiceType;

    @JsonProperty("tax_type")
    private String taxType;

    @JsonProperty("invoice_code")
    private String invoiceCode;

    @JsonProperty("print_times")
    private int printTimes;

    @JsonProperty("approve_status")
    private String approveStatus;

    @JsonProperty("doc_date")
    private String docDate;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("trans_curr_amount")
    private double transCurrAmount;

    @JsonProperty("deduction_amount")
    private double deductionAmount;

    @JsonProperty("trans_curr_tax")
    private double transCurrTax;

    @JsonProperty("purchase_expenses")
    private double purchaseExpenses;

    @JsonProperty("supplier_full_name")
    private String supplierFullName;

    @JsonProperty("tax_identification_no")
    private String taxIdentificationNo;

    @JsonProperty("pieces")
    private int pieces;

    @JsonProperty("total_accepted_qty")
    private int totalAcceptedQty;

    @JsonProperty("invoice_date")
    private String invoiceDate;

    @JsonProperty("trans_curr_not_tax_amount")
    private double transCurrNotTaxAmount;

    @JsonProperty("tax_rate")
    private double taxRate;

    @JsonProperty("local_curr_not_tax_amount")
    private double localCurrNotTaxAmount;

    @JsonProperty("local_curr_tax")
    private double localCurrTax;

    @JsonProperty("payment_condition_no")
    private String paymentConditionNo;

    @JsonProperty("total_accepted_package_qty")
    private int totalAcceptedPackageQty;

    @JsonProperty("self_funding_offset_local_curr")
    private double selfFundingOffsetLocalCurr;

    @JsonProperty("approval_status_code")
    private String approvalStatusCode;

    @JsonProperty("register_book_no")
    private String registerBookNo;

    @JsonProperty("transfer_times")
    private int transferTimes;

    @JsonProperty("source_type_no")
    private String sourceTypeNo;

    @JsonProperty("source_doc_no")
    private String sourceDocNo;

    @JsonProperty("department")
    private String department;

    @JsonProperty("ebc_export_code")
    private String ebcExportCode;

    @JsonProperty("total_arrival_qty")
    private int totalArrivalQty;

    @JsonProperty("total_arrival_package_qty")
    private int totalArrivalPackageQty;

    @JsonProperty("ebc_shipping_notice_doc_no")
    private String ebcShippingNoticeDocNo;

    @JsonProperty("ebc_shipping_notice_version")
    private String ebcShippingNoticeVersion;

    @JsonProperty("source_code")
    private String sourceCode;

    @JsonProperty("project_no")
    private String projectNo;

    @JsonProperty("doc_type_name")
    private String docTypeName;

    @JsonProperty("plant_name")
    private String plantName;

    @JsonProperty("supplier_name")
    private String supplierName;

    @JsonProperty("total_trans_curr_amount")
    private double totalTransCurrAmount;

    @JsonProperty("total_local_curr_amount")
    private double totalLocalCurrAmount;

    @JsonProperty("payment_condition_name")
    private String paymentConditionName;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("project_name")
    private String projectName;

    @JsonProperty("purchase_receipt_detail_data")
    private List<GoodsReceiptDetail> goodsReceiptDetailList;
}