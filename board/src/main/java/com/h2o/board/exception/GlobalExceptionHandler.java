package com.h2o.board.exception;

import com.h2o.board.response.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDto> badRequestException(BadRequestException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(e.getCode());
        responseDto.setStatus(HttpStatus.BAD_REQUEST);
        responseDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseDto> unauthorizedException(UnauthorizedException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(e.getCode());
        responseDto.setStatus(HttpStatus.UNAUTHORIZED);
        responseDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDto> notFoundException(NotFoundException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(e.getCode());
        responseDto.setStatus(HttpStatus.NOT_FOUND);
        responseDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
}
