package com.trilight.ocr.client.ocr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WordsResult {
    @JsonProperty("InvoiceNumDigit")
    private String invoiceNumDigit;

    @JsonProperty("ServiceType")
    private String serviceType;

    @JsonProperty("InvoiceNum")
    private String invoiceNum;

    @JsonProperty("InvoiceNumConfirm")
    private String invoiceNumConfirm;

    @JsonProperty("SellerName")
    private String sellerName;

    @JsonProperty("CommodityTaxRate")
    private List<CommodityTaxRate> commodityTaxRate;

    @JsonProperty("SellerBank")
    private String sellerBank;

    @JsonProperty("Checker")
    private String checker;

    @JsonProperty("TotalAmount")
    private String totalAmount;

    @JsonProperty("CommodityAmount")
    private List<CommodityAmount> commodityAmount;

    @JsonProperty("InvoiceDate")
    private String invoiceDate;

    @JsonProperty("CommodityTax")
    private List<CommodityTax> commodityTax;

    @JsonProperty("PurchaserName")
    private String purchaserName;

    @JsonProperty("CommodityNum")
    private List<CommodityNum> commodityNum;

    @JsonProperty("Province")
    private String province;

    @JsonProperty("City")
    private String city;

    @JsonProperty("SheetNum")
    private String sheetNum;

    @JsonProperty("Agent")
    private String agent;

    @JsonProperty("PurchaserBank")
    private String purchaserBank;

    @JsonProperty("Remarks")
    private String remarks;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("SellerAddress")
    private String sellerAddress;

    @JsonProperty("PurchaserAddress")
    private String purchaserAddress;

    @JsonProperty("InvoiceCode")
    private String invoiceCode;

    @JsonProperty("InvoiceCodeConfirm")
    private String invoiceCodeConfirm;

    @JsonProperty("CommodityUnit")
    private List<CommodityUnit> commodityUnit;

    @JsonProperty("Payee")
    private String payee;

    @JsonProperty("PurchaserRegisterNum")
    private String purchaserRegisterNum;

    @JsonProperty("CommodityPrice")
    private List<CommodityPrice> commodityPrice;

    @JsonProperty("NoteDrawer")
    private String noteDrawer;

    @JsonProperty("AmountInWords")
    private String amountInWords;

    @JsonProperty("AmountInFiguers")
    private String amountInFiguers;

    @JsonProperty("TotalTax")
    private String totalTax;

    @JsonProperty("InvoiceType")
    private String invoiceType;

    @JsonProperty("SellerRegisterNum")
    private String sellerRegisterNum;

    @JsonProperty("CommodityName")
    private List<CommodityName> commodityName;

    @JsonProperty("CommodityType")
    private List<CommodityType> commodityType;

    @JsonProperty("CommodityPlateNum")
    private List<String> commodityPlateNum;

    @JsonProperty("CommodityVehicleType")
    private List<String> commodityVehicleType;

    @JsonProperty("CommodityStartDate")
    private List<String> commodityStartDate;

    @JsonProperty("CommodityEndDate")
    private List<String> commodityEndDate;

    @JsonProperty("OnlinePay")
    private String onlinePay;
}
