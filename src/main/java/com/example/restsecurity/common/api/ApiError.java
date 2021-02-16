package com.example.restsecurity.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class ApiError {


    private String message;

    private HttpStatus status;

    public static ApiError DEFAULT = new ApiError("Action is done", HttpStatus.ACCEPTED);

}
