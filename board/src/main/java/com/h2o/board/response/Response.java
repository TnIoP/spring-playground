package com.h2o.board.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class Response {
    private int statusCode;
    private HttpStatus status;
    private String message;
}
