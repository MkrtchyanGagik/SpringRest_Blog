package com.example.restsecurity.common.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(int commentId){

        super(String.format("Comment by id :{%d} not found",commentId));

    }

}
