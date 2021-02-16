package com.example.restsecurity.common.api;

import com.example.restsecurity.common.exception.CategoryNotFoundException;
import com.example.restsecurity.common.exception.PostNotFoundException;
import com.example.restsecurity.common.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class, CategoryNotFoundException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex) {
        if (ex instanceof UserNotFoundException) {
            UserNotFoundException unfe = (UserNotFoundException) ex;
            return ResponseEntity.ok(new ApiError(unfe.getMessage(), HttpStatus.NOT_FOUND));
        }
        if (ex instanceof PostNotFoundException) {
            PostNotFoundException pnfe = (PostNotFoundException) ex;
            return ResponseEntity.ok(new ApiError(pnfe.getMessage(), HttpStatus.NOT_FOUND));


        }

        if (ex instanceof CategoryNotFoundException) {
            CategoryNotFoundException cnfe = (CategoryNotFoundException) ex;

            return ResponseEntity.ok(new ApiError(cnfe.getMessage(), HttpStatus.NOT_FOUND));


        }
        return ResponseEntity.ok(ApiError.DEFAULT);
    }

}
