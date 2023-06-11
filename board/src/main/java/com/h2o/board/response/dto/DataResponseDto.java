package com.h2o.board.response.dto;

public class DataResponseDto<T> extends ResponseDto {
    private final T data;

    private DataResponseDto(T data) {
        super();
        this.data = data;
    }

    public static <T> DataResponseDto<T> of(T data) {
        return new DataResponseDto<>(data);
    }

    public static <T> DataResponseDto<T> empty() {
        return new DataResponseDto<>(null);
    }
}
