package com.example.withplaceprototype.utils;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int code;

    private String message;

    private T data;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(int code, String message, T data){
        return new ApiResponse<>(code, message, data);
    }
}