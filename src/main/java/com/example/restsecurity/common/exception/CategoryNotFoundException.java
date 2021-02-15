package com.example.restsecurity.common.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(int categoryId){

        super(String.format("user by id: {%d} does not exist",categoryId));
    }
}
