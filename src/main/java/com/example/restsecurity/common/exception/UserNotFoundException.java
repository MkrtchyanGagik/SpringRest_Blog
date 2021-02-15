package com.example.restsecurity.common.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(int userId){
        super(String.format("user by id: {%d} does not exist",userId));
    }



}
