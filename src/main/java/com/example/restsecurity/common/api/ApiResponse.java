package com.example.restsecurity.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private String message;

    private int status;

    private T data;


    public ApiResponse(int status, T data) {
        this.status = status;
        this.data = data;

    }
    public ApiResponse(String message,int status){
        this.message=message;
        this.status=status;
    }
}
