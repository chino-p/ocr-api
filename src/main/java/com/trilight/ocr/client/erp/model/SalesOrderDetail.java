package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalesOrderDetail {

    @JsonProperty("doc_type_no")
    private String docTypeNo;

    @JsonProperty("doc_no")
    private String docNo;

    @JsonProperty("seq")
    private String seq;

    @JsonProperty("item_no")
    private String itemNo;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_spec")
    private String itemSpec;

    @JsonProperty("warehouse_no")
    private String warehouseNo;

    @JsonProperty("order_qty")
    private int orderQty;

    @JsonProperty("delivered_qty")
    private int deliveredQty;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("plan_delivery_date")
    private String planDeliveryDate;

    @JsonProperty("customer_item_no")
    private String customerItemNo;

    @JsonProperty("sales_forecast")
    private String salesForecast;

    @JsonProperty("is_end")
    private String isEnd;

    @JsonProperty("source_doc_type")
    private String sourceDocType;

    @JsonProperty("source_doc_no")
    private String sourceDocNo;

    @JsonProperty("source_seq")
    private String sourceSeq;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("approve_status")
    private String approveStatus;

    @JsonProperty("inventory_qty")
    private int inventoryQty;

    @JsonProperty("small_unit")
    private String smallUnit;

    @JsonProperty("gift_qty")
    private int giftQty;

    @JsonProperty("delivered_gift_qty")
    private int deliveredGiftQty;

    @JsonProperty("discount_rate")
    private double discountRate;

    @JsonProperty("project_no")
    private String projectNo;

    @JsonProperty("forecast_seq")
    private String forecastSeq;

    @JsonProperty("package_method")
    private String packageMethod;

    @JsonProperty("gross_weight_kg")
    private double grossWeightKg;

    @JsonProperty("cuft_size")
    private double cuftSize;

    @JsonProperty("order_package_qty")
    private int orderPackageQty;

    @JsonProperty("delivered_package_qty")
    private int deliveredPackageQty;

    @JsonProperty("gift_package_qty")
    private int giftPackageQty;

    @JsonProperty("delivered_gift_package_qty")
    private int deliveredGiftPackageQty;

    @JsonProperty("package_unit")
    private String packageUnit;

    @JsonProperty("tax_rate")
    private double taxRate;

    @JsonProperty("pre_tax amount")
    private double preTaxAmount;

    @JsonProperty("tax")
    private double tax;

    @JsonProperty("pieces_per")
    private int piecesPer;

    @JsonProperty("pieces")
    private int pieces;

    @JsonProperty("wholesale_price")
    private double wholesalePrice;

    @JsonProperty("retail_price")
    private double retailPrice;

    @JsonProperty("shipping_sales_qty")
    private int shippingSalesQty;

    @JsonProperty("shipping_sales_package_qty")
    private int shippingSalesPackageQty;

    @JsonProperty("original_customer_no")
    private String originalCustomerNo;

    @JsonProperty("configuration_no")
    private String configurationNo;

    @JsonProperty("simulation_unit_cost")
    private double simulationUnitCost;

    @JsonProperty("simulation_cost")
    private double simulationCost;

    @JsonProperty("estimate_gross_margin")
    private double estimateGrossMargin;

    @JsonProperty("estimate_gross_amount")
    private double estimateGrossAmount;

    @JsonProperty("lend_not_sales_qty")
    private int lendNotSalesQty;

    @JsonProperty("lend_not_sales_package_qty")
    private int lendNotSalesPackageQty;

    @JsonProperty("contract_type")
    private String contractType;

    @JsonProperty("instalment_stage")
    private String instalmentStage;

    @JsonProperty("is_contract_instalment")
    private String isContractInstalment;

    @JsonProperty("is_parts_distribution")
    private String isPartsDistribution;

    @JsonProperty("component_receive_warehouse_no")
    private String componentReceiveWarehouseNo;

    @JsonProperty("mo_doc_type")
    private String moDocType;

    @JsonProperty("mo_doc_no")
    private String moDocNo;

    @JsonProperty("location_no")
    private String locationNo;

    @JsonProperty("local_curr_not_tax_amount")
    private double localCurrNotTaxAmount;

    @JsonProperty("local_curr_tax")
    private double localCurrTax;

    @JsonProperty("local_curr_amount")
    private double localCurrAmount;

    @JsonProperty("warehouse_name")
    private String warehouseName;

    @JsonProperty("package_method_name")
    private String packageMethodName;

    @JsonProperty("customer_item_name")
    private String customerItemName;

    @JsonProperty("customer_item_spec")
    private String customerItemSpec;

    @JsonProperty("component_receive_warehouse_name")
    private String componentReceiveWarehouseName;
}
