package com.example.restsecurity.common.exception;

public class PostNotFoundException extends RuntimeException{

    public  PostNotFoundException(int postId){

        super(String.format("post by id: {%d} does not exist",postId));
    }
}
