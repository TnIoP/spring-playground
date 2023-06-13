package com.h2o.board.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{
    public final static int STATUS_CODE = 401;
    public int getCode() {
        return STATUS_CODE;
    }
    public UnauthorizedException(String message) {
        super(message);
    }
}
