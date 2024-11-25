package com.trilight.ocr.client.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsReceiptDetail {

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

    @JsonProperty("arrival_qty")
    private BigDecimal arrivalQty;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("warehouse_no")
    private String warehouseNo;

    @JsonProperty("lot_no")
    private String lotNo;

    @JsonProperty("purchase_type_no")
    private String purchaseTypeNo;

    @JsonProperty("purchase_no")
    private String purchaseNo;

    @JsonProperty("purchase_seq")
    private String purchaseSeq;

    @JsonProperty("accepted_date")
    private String acceptedDate;

    @JsonProperty("accepted_qty")
    private int acceptedQty;

    @JsonProperty("pricing_qty")
    private int pricingQty;

    @JsonProperty("return_qty")
    private int returnQty;

    @JsonProperty("price")
    private double price;

    @JsonProperty("trans_curr_amount")
    private double transCurrAmount;

    @JsonProperty("deduction_amount")
    private double deductionAmount;

    @JsonProperty("borrowing_type_no")
    private String borrowingTypeNo;

    @JsonProperty("borrowing_doc_no")
    private String borrowingDocNo;

    @JsonProperty("borrowing_seq")
    private String borrowingSeq;

    @JsonProperty("purchase_expenses")
    private double purchaseExpenses;

    @JsonProperty("deduction_desc")
    private String deductionDesc;

    @JsonProperty("hold_payment")
    private String holdPayment;

    @JsonProperty("overdue_code")
    private String overdueCode;

    @JsonProperty("qc_status")
    private String qcStatus;

    @JsonProperty("reject_code")
    private String rejectCode;

    @JsonProperty("approve_status")
    private String approveStatus;

    @JsonProperty("invoice_code")
    private String invoiceCode;

    @JsonProperty("update_code")
    private String updateCode;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("inventory_qty")
    private int inventoryQty;

    @JsonProperty("small_unit")
    private String smallUnit;

    @JsonProperty("expiry_date")
    private String expiryDate;

    @JsonProperty("reinspection_date")
    private String reinspectionDate;

    @JsonProperty("approver_no")
    private String approverNo;

    @JsonProperty("project_no")
    private String projectNo;

    @JsonProperty("generate_entry_code")
    private String generateEntryCode;

    @JsonProperty("self_funding_offset_code")
    private String selfFundingOffsetCode;

    @JsonProperty("trans_curr_not_tax_amount")
    private double transCurrNotTaxAmount;

    @JsonProperty("trans_curr_tax")
    private double transCurrTax;

    @JsonProperty("local_curr_not_tax_amount")
    private double localCurrNotTaxAmount;

    @JsonProperty("local_curr_tax")
    private double localCurrTax;

    @JsonProperty("arrival_package_qty")
    private int arrivalPackageQty;

    @JsonProperty("accepted_package_qty")
    private int acceptedPackageQty;

    @JsonProperty("return_package_qty")
    private int returnPackageQty;

    @JsonProperty("package_unit")
    private String packageUnit;

    @JsonProperty("invoiced_qty")
    private int invoicedQty;

    @JsonProperty("invoiced")
    private int invoiced;

    @JsonProperty("production_date")
    private String productionDate;

    @JsonProperty("pieces_per")
    private int piecesPer;

    @JsonProperty("pieces")
    private int pieces;

    @JsonProperty("scrap_qty")
    private int scrapQty;

    @JsonProperty("scrap_package_qty")
    private int scrapPackageQty;

    @JsonProperty("pricing_unit")
    private String pricingUnit;

    @JsonProperty("inventory_unit")
    private String inventoryUnit;

    @JsonProperty("lot_description")
    private String lotDescription;

    @JsonProperty("destroyed_qty")
    private int destroyedQty;

    @JsonProperty("destroyed_package_qty")
    private int destroyedPackageQty;

    @JsonProperty("scrap_status")
    private String scrapStatus;

    @JsonProperty("location_no")
    private String locationNo;

    @JsonProperty("delivery_period")
    private String deliveryPeriod;

    @JsonProperty("accepted_gift_prepare_item_package_qty")
    private int acceptedGiftPrepareItemPackageQty;

    @JsonProperty("estimate_code")
    private String estimateCode;

    @JsonProperty("accepted_gift_prepare_item_qty")
    private int acceptedGiftPrepareItemQty;

    @JsonProperty("type")
    private String type;

    @JsonProperty("arrival_type_no")
    private String arrivalTypeNo;

    @JsonProperty("arrival_doc_no")
    private String arrivalDocNo;

    @JsonProperty("arrival_seq")
    private String arrivalSeq;

    @JsonProperty("inspection_batch")
    private String inspectionBatch;

    @JsonProperty("qms_status")
    private String qmsStatus;

    @JsonProperty("qms_no")
    private String qmsNo;

    @JsonProperty("warehouse_name")
    private String warehouseName;

    @JsonProperty("approver_name")
    private String approverName;

    @JsonProperty("purchase_receipt_serial_data")
    private List<Object> purchaseReceiptSerialData;
}