package com.trilight.ocr.handler;

import com.trilight.ocr.common.model.R;
import com.trilight.ocr.exception.BizException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public R<Void> handleCustomException(BizException ex, HttpServletRequest request) {
        log.error("请求地址'{}',业务异常'{}'", request.getRequestURI(), ex.getMessage(), ex);
        return R.fail(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public R<Void> handleDuplicateKeyException(DuplicateKeyException ex) {
        String message = ex.getMessage();
        if (message.contains("vat_invoice")) {
            return R.fail(101, "发票号已存在");
        } else {
            return R.fail(102, "数据已存在");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception ex, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',业务异常'{}'", requestUri, ex.getMessage(), ex);
        return R.fail(HttpStatus.BAD_REQUEST.value(), String.format("接口'%s'异常, 请联系管理员", requestUri));
    }
}