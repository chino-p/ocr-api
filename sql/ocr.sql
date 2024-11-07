CREATE TABLE vat_invoice
(
    vat_invoice_id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '发票ID',

    purchaser_name           VARCHAR(255)   NOT NULL COMMENT '购买方名称',
    seller_name              VARCHAR(255)   NOT NULL COMMENT '销售方名称',
    invoice_num              VARCHAR(50)    NOT NULL COMMENT '发票号码',
    invoice_date             DATE           NOT NULL COMMENT '开票日期',
    total_amount_include_tax DECIMAL(18, 2) NOT NULL COMMENT '价税合计',
    total_tax                DECIMAL(18, 2) NOT NULL COMMENT '合计税额',
    total_amount             DECIMAL(18, 2) NOT NULL COMMENT '不含税合计金额',

    create_by                VARCHAR(20) COMMENT '创建人ID',
    create_user              VARCHAR(20) COMMENT '创建人名称',
    update_by                VARCHAR(20) COMMENT '更新人ID',
    update_user              VARCHAR(20) COMMENT '更新人名称',
    create_time              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='增值税发票表';

CREATE TABLE commodity
(
    commodity_id       BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    vat_invoice_id     BIGINT          NOT NULL COMMENT '关联的增值税发票ID',

    commodity_name     VARCHAR(255)    NOT NULL COMMENT '项目名称',
    commodity_type     VARCHAR(100) COMMENT '规格型号',
    commodity_unit     VARCHAR(50) COMMENT '单位',
    commodity_price    DECIMAL(18, 10) NOT NULL COMMENT '单价',
    commodity_num      DECIMAL(18, 2)  NOT NULL COMMENT '数量',
    commodity_amount   DECIMAL(18, 2)  NOT NULL COMMENT '金额',
    commodity_tax_rate DECIMAL(5, 2) COMMENT '税率',
    commodity_tax      DECIMAL(18, 2) COMMENT '税额',
    create_by          VARCHAR(20) COMMENT '创建人ID',
    create_user        VARCHAR(20) COMMENT '创建人名称',
    update_by          VARCHAR(20) COMMENT '更新人ID',
    update_user        VARCHAR(20) COMMENT '更新人名称',
    create_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品明细表';