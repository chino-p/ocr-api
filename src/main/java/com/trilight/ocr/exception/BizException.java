package com.trilight.ocr.exception;

import com.trilight.ocr.enums.BizCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    private int code;
    private String msg;

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMsg());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMsg();
    }
}