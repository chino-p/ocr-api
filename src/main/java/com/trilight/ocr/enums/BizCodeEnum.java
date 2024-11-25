package com.trilight.ocr.enums;

import lombok.Getter;

@Getter
public enum BizCodeEnum {

    // 定义通用错误码
    INVALID_REQUEST(400, "请求参数不正确"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "没有访问权限"),
    NOT_FOUND(404, "资源未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    // 定义服务器端错误
    INVALID_SORT_PARAM(10001, "排序参数有误"),

    // 定义业务错误码
    USER_NOT_FOUND(1001, "用户不存在"),
    ORDER_NOT_FOUND(1002, "订单不存在"),
    PRODUCT_OUT_OF_STOCK(1003, "商品库存不足"),

    // 文件相关
    SUPPORT_PDF_ONLY(2001, "仅支持pdf格式"),
    FILE_PROCESS_ERROR(2002, "文件处理异常"),
    PARSE_CODE_ERROR(2003,"二维码解析失败" );

    private final int code;
    private final String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}