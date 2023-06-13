package com.h2o.board.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    public final static int STATUS_CODE = 400;
    public int getCode() {
        return STATUS_CODE;
    }
    public BadRequestException(String message) {
        super(message);
    }
}
