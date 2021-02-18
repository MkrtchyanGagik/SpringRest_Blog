package com.example.restsecurity.transform.requset.comment;

import lombok.Data;

@Data
public class CommentCreateRequest {


    private int UserId;

    private int PostId;

    private String commentBody;

}
