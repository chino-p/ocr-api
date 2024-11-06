## 百度发票ocr字段说明

* `words_result`
  * `PurchaserName` 购买方名称
  * `SellerName` 销售方名称
  * `InvoiceNum` 发票号码
  * `InvoiceDate` 开票日期
  * `AmountInFiguers` 价税合计
  * `CommodityName` 项目名称
  * `CommodityType` 规格型号
  * `CommodityUnit` 单位
  * `CommodityNum` 数量
  * `CommodityPrice` 单价
  * `CommodityAmount` 金额
  * `CommodityTaxRate` 税率
  * `CommodityTax` 税额
  * `TotalTax` 合计税额
  * `TotalAmount` 不含税合计金额

```json
{
  "words_result": {
    "PurchaserAddress": "",
    "PurchaserBank": "",
    "Password": "",
    "CommodityVehicleType": [],
    "SellerRegisterNum": "91310117MA7CFDKT22",
    "SellerBank": "",
    "CommodityNum": [
      {
        "row": "1",
        "word": "50"
      },
      {
        "row": "2",
        "word": "50"
      },
      {
        "row": "3",
        "word": "100"
      }
    ],
    "CommodityAmount": [
      {
        "row": "1",
        "word": "1619.47"
      },
      {
        "row": "2",
        "word": "973.45"
      },
      {
        "row": "3",
        "word": "97.35"
      }
    ],
    "InvoiceType": "电子发票(专用发票)",
    "AmountInWords": "叁仟零肆拾圆整",
    "TotalTax": "349.73",
    "MachineCode": "",
    "City": "",
    "InvoiceNumDigit": "",
    "Checker": "",
    "InvoiceCode": "",
    "SellerAddress": "",
    "CommodityPrice": [
      {
        "row": "1",
        "word": "32.3893805309735"
      },
      {
        "row": "2",
        "word": "19.4690265486726"
      },
      {
        "row": "3",
        "word": "0.9734513274336"
      }
    ],
    "NoteDrawer": "范向军",
    "Province": "",
    "InvoiceNum": "24312000000081882251",
    "CommodityTaxRate": [
      {
        "row": "1",
        "word": "13%"
      },
      {
        "row": "2",
        "word": "13%"
      },
      {
        "row": "3",
        "word": "13%"
      }
    ],
    "ServiceType": "电器设备",
    "InvoiceDate": "2024年03月26日",
    "CommodityEndDate": [],
    "PurchaserRegisterNum": "9142010005200621X4",
    "CommodityStartDate": [],
    "TotalAmount": "2690.27",
    "SheetNum": "",
    "CommodityPlateNum": [],
    "PurchaserName": "武汉钧恒科技有限公司",
    "SellerName": "上海蘅滨电子有限公司",
    "InvoiceNumConfirm": "24312000000081882251",
    "Agent": "否",
    "InvoiceTag": "其他",
    "CommodityUnit": [
      {
        "row": "1",
        "word": "PCS"
      },
      {
        "row": "2",
        "word": "PCS"
      },
      {
        "row": "3",
        "word": "PCS"
      }
    ],
    "CheckCode": "",
    "InvoiceTypeOrg": "电子发票(增值税专用发票)",
    "Remarks": "H-JH-24012501XS20240100018;收款人:刘良玉;复核人:宋新爱;",
    "Payee": "",
    "CommodityTax": [
      {
        "row": "1",
        "word": "210.53"
      },
      {
        "row": "2",
        "word": "126.55"
      },
      {
        "row": "3",
        "word": "12.65"
      }
    ],
    "AmountInFiguers": "3040.00",
    "CommodityName": [
      {
        "row": "1",
        "word": "*电子元件*发端陶瓷基板"
      },
      {
        "row": "2",
        "word": "*电子元件*收端陶瓷基板"
      },
      {
        "row": "3",
        "word": "*电子元件*散热陶瓷块"
      }
    ],
    "CommodityType": [
      {
        "row": "1",
        "word": "TL-PM23010C-S03"
      },
      {
        "row": "2",
        "word": "TL-PM23010C-S04"
      },
      {
        "row": "3",
        "word": "2.97*1.50*0.42mm"
      }
    ],
    "OnlinePay": ""
  },
  "words_result_num": 45,
  "pdf_file_size": 1,
  "log_id": 1854032165653729124
}
```