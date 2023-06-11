package com.h2o.board.exception;

import com.h2o.board.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> badRequestException(BadRequestException e) {
        Response response = new Response();
        response.setStatusCode(e.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Response> unauthorizedException(UnauthorizedException e) {
        Response response = new Response();
        response.setStatusCode(e.getCode());
        response.setStatus(HttpStatus.UNAUTHORIZED);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> notFoundException(NotFoundException e) {
        Response response = new Response();
        response.setStatusCode(e.getCode());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
