package com.h2o.board.global.response.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private int statusCode;
    private HttpStatus status;
    private String message;
}
