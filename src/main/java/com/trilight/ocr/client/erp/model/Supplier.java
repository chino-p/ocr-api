package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Supplier {

    @JsonProperty("supplier_no")
    private String supplierNo;

    @JsonProperty("supplier_name")
    private String supplierName;

    @JsonProperty("supplier_fullname")
    private String supplierFullname;

    @JsonProperty("supplier_classification")
    private String supplierClassification;

    @JsonProperty("tax_identification_no")
    private String taxIdentificationNo;

    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;

    @JsonProperty("telephone_1")
    private String telephone1;

    @JsonProperty("telephone_2")
    private String telephone2;

    @JsonProperty("fax_no")
    private String faxNo;

    @JsonProperty("e_mail")
    private String email;

    @JsonProperty("company_owner")
    private String companyOwner;

    @JsonProperty("contact1")
    private String contact1;

    @JsonProperty("contact_address_1")
    private String contactAddress1;

    @JsonProperty("contact_address_2")
    private String contactAddress2;

    @JsonProperty("valid_status")
    private String validStatus;

    @JsonProperty("open_date")
    private String openDate;

    @JsonProperty("capital_amount")
    private Double capitalAmount;

    @JsonProperty("headcount")
    private Integer headcount;

    @JsonProperty("po_delivery_method")
    private String poDeliveryMethod;

    @JsonProperty("trans_currency")
    private String transCurrency;

    @JsonProperty("1st_transaction")
    private String firstTransaction;

    @JsonProperty("latest_trans_date")
    private String latestTransDate;

    @JsonProperty("settlement_method_no")
    private String settlementMethodNo;

    @JsonProperty("payment_condition_no")
    private String paymentConditionNo;

    @JsonProperty("price_condition")
    private String priceCondition;

    @JsonProperty("remittance_bank")
    private String remittanceBank;

    @JsonProperty("remittance_account")
    private String remittanceAccount;

    @JsonProperty("note_delivery_method")
    private String noteDeliveryMethod;

    @JsonProperty("invoice_type")
    private String invoiceType;

    @JsonProperty("abc_level")
    private String abcLevel;

    @JsonProperty("delivery_level")
    private String deliveryLevel;

    @JsonProperty("quality_rating")
    private String qualityRating;

    @JsonProperty("closing_month")
    private String closingMonth;

    @JsonProperty("closing_date")
    private String closingDate;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("payable_account_no")
    private String payableAccountNo;

    @JsonProperty("processing_account_no")
    private String processingAccountNo;

    @JsonProperty("bill_account_no")
    private String billAccountNo;

    @JsonProperty("tax_type")
    private String taxType;

    @JsonProperty("allow_batch_delivery")
    private String allowBatchDelivery;

    @JsonProperty("zip_code1")
    private String zipCode1;

    @JsonProperty("purchaser")
    private String purchaser;

    @JsonProperty("contact2")
    private String contact2;

    @JsonProperty("contact3")
    private String contact3;

    @JsonProperty("zip_code2")
    private String zipCode2;

    @JsonProperty("bill_addr_1")
    private String billAddr1;

    @JsonProperty("bill_addr_2")
    private String billAddr2;

    @JsonProperty("doc_printing_format")
    private String docPrintingFormat;

    @JsonProperty("deposit_rate")
    private Double depositRate;

    @JsonProperty("tax_rate")
    private Double taxRate;

    @JsonProperty("shortcut")
    private String shortcut;

    @JsonProperty("delivery_periods")
    private Integer deliveryPeriods;

    @JsonProperty("prepayment_account_no")
    private String prepaymentAccountNo;

    @JsonProperty("export_to_ebc")
    private String exportToEbc;

    @JsonProperty("expense_department_code")
    private String expenseDepartmentCode;

    @JsonProperty("warehouse")
    private String warehouse;

    @JsonProperty("ebc_apply_no")
    private String ebcApplyNo;

    @JsonProperty("ebc_export_code")
    private String ebcExportCode;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("region_name")
    private String regionName;

    @JsonProperty("supplier_classification_name")
    private String supplierClassificationName;

    @JsonProperty("payable_account_name")
    private String payableAccountName;

    @JsonProperty("bill_account_name")
    private String billAccountName;

    @JsonProperty("processing_account_name")
    private String processingAccountName;

    @JsonProperty("currency_name")
    private String currencyName;

    @JsonProperty("purchaser_name")
    private String purchaserName;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("prepayment_account_name")
    private String prepaymentAccountName;

    @JsonProperty("settlement_method_name")
    private String settlementMethodName;

    @JsonProperty("expense_department_name")
    private String expenseDepartmentName;

    @JsonProperty("warehouse_name")
    private String warehouseName;


}
