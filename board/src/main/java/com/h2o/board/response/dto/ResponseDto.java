package com.h2o.board.response.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ResponseDto {
    private int statusCode;
    private HttpStatus status;
    private String message;
}
