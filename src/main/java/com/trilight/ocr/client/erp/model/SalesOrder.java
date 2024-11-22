package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SalesOrder {
    @JsonProperty("doc_type_no")
    private String docTypeNo;

    @JsonProperty("doc_no")
    private String docNo;

    @JsonProperty("order_date")
    private String orderDate;

    @JsonProperty("customer_no")
    private String customerNo;

    @JsonProperty("department")
    private String department;

    @JsonProperty("salesman_no")
    private String salesmanNo;

    @JsonProperty("plant_no")
    private String plantNo;

    @JsonProperty("trans_currency")
    private String transCurrency;

    @JsonProperty("exchange_rate")
    private double exchangeRate;

    @JsonProperty("delivery_address1")
    private String deliveryAddress1;

    @JsonProperty("delivery_address2")
    private String deliveryAddress2;

    @JsonProperty("customer_doc_no")
    private String customerDocNo;

    @JsonProperty("price_condition")
    private String priceCondition;

    @JsonProperty("payment_condition_no")
    private String paymentConditionNo;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("tax_type")
    private String taxType;

    @JsonProperty("l_cno")
    private String lcNo;

    @JsonProperty("contact")
    private String contact;

    @JsonProperty("transport_mode_no")
    private String transportModeNo;

    @JsonProperty("departure_port")
    private String departurePort;

    @JsonProperty("destination_port")
    private String destinationPort;

    @JsonProperty("agent")
    private String agent;

    @JsonProperty("broker")
    private String broker;

    @JsonProperty("inspection_company")
    private String inspectionCompany;

    @JsonProperty("transport_company_no")
    private String transportCompanyNo;

    @JsonProperty("commission_rate")
    private double commissionRate;

    @JsonProperty("approve_status")
    private String approveStatus;

    @JsonProperty("print_times")
    private int printTimes;

    @JsonProperty("order_amount")
    private double orderAmount;

    @JsonProperty("order_tax")
    private double orderTax;

    @JsonProperty("tot_qty")
    private int totQty;

    @JsonProperty("consignee")
    private String consignee;

    @JsonProperty("notify")
    private String notify;

    @JsonProperty("mark_number")
    private String markNumber;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("correspondent_bank")
    private String correspondentBank;

    @JsonProperty("invoice_remarks")
    private String invoiceRemarks;

    @JsonProperty("package_list_remarks")
    private String packageListRemarks;

    @JsonProperty("doc_date")
    private String docDate;

    @JsonProperty("approver_no")
    private String approverNo;

    @JsonProperty("tax_rate")
    private double taxRate;

    @JsonProperty("total_gross_weight_kg")
    private double totalGrossWeightKg;

    @JsonProperty("total_cuft_size")
    private double totalCuftSize;

    @JsonProperty("deposit_rate")
    private double depositRate;

    @JsonProperty("total_package_qty")
    private int totalPackageQty;

    @JsonProperty("negotiating_bank")
    private String negotiatingBank;

    @JsonProperty("approval_status_code")
    private String approvalStatusCode;

    @JsonProperty("process_no")
    private String processNo;

    @JsonProperty("post_status")
    private String postStatus;

    @JsonProperty("downstream_supplier")
    private String downstreamSupplier;

    @JsonProperty("remarks_1")
    private String remarks1;

    @JsonProperty("remarks_2")
    private String remarks2;

    @JsonProperty("remarks_3")
    private String remarks3;

    @JsonProperty("remarks_4")
    private String remarks4;

    @JsonProperty("mark")
    private String mark;

    @JsonProperty("side_mark")
    private String sideMark;

    @JsonProperty("transfer_times")
    private int transferTimes;

    @JsonProperty("ebc_export_code")
    private String ebcExportCode;

    @JsonProperty("ebc_so_no")
    private String ebcSoNo;

    @JsonProperty("ebc_so_version")
    private String ebcSoVersion;

    @JsonProperty("source_code")
    private String sourceCode;

    @JsonProperty("invoice_amount")
    private double invoiceAmount;

    @JsonProperty("invoice_tax")
    private double invoiceTax;

    @JsonProperty("over_limit")
    private String overLimit;

    @JsonProperty("contract_type")
    private String contractType;

    @JsonProperty("tax_code")
    private String taxCode;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("fax_no")
    private String faxNo;

    @JsonProperty("project_no")
    private String projectNo;

    @JsonProperty("delivery_date")
    private String deliveryDate;

    @JsonProperty("installation_completion_date")
    private String installationCompletionDate;

    @JsonProperty("local_curr_not_tax_amount")
    private double localCurrNotTaxAmount;

    @JsonProperty("local_curr_tax")
    private double localCurrTax;

    @JsonProperty("total_amt_tc")
    private double totalAmtTc;

    @JsonProperty("doc_type_name")
    private String docTypeName;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("sales_name")
    private String salesName;

    @JsonProperty("plant_name")
    private String plantName;

    @JsonProperty("agent_name")
    private String agentName;

    @JsonProperty("broker_name")
    private String brokerName;

    @JsonProperty("inspection_company_name")
    private String inspectionCompanyName;

    @JsonProperty("transport_company_name")
    private String transportCompanyName;

    @JsonProperty("consignee_name")
    private String consigneeName;

    @JsonProperty("notify_name")
    private String notifyName;

    @JsonProperty("approver_name")
    private String approverName;

    @JsonProperty("payment_condition_name")
    private String paymentConditionName;

    @JsonProperty("negotiating_bank_name")
    private String negotiatingBankName;

    @JsonProperty("process_name")
    private String processName;

    @JsonProperty("project_name")
    private String projectName;
}