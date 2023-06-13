package com.h2o.board.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public final static int STATUS_CODE = 404;
    public int getCode() {
        return STATUS_CODE;
    }
    public NotFoundException(String message) {
        super(message);
    }
}
