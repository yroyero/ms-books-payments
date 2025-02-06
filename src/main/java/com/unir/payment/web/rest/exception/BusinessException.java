package com.unir.payment.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException {

    private static final int code = 422;

    public BusinessException(String message) {
        super(message);
    }

    public int getCode() {
        return this.code;
    }
}
