package com.h2o.board.response.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor

public class DataResponseDto<T> extends ResponseDto {
    private T data;

    private DataResponseDto(T data) {
        super(200, HttpStatus.OK, "success");
        this.data = data;
    }

    public static <T> DataResponseDto<T> of(T data) {
        return new DataResponseDto<>(data);
    }
}
