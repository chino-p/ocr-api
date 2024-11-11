package com.trilight.ocr.common.model;

import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

public record R<T>(int code, String msg, T data) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static <T> R<T> ok() {
        return new R<>(HttpStatus.OK.value(), "操作成功", null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(HttpStatus.OK.value(), "操作成功", data);
    }

    public static <T> R<T> ok(String msg) {
        return new R<>(HttpStatus.OK.value(), msg, null);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<>(HttpStatus.OK.value(), msg, data);
    }

    public static <T> R<T> fail() {
        return new R<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败", null);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg, null);
    }
}
