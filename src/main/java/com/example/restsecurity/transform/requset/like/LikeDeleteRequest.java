package com.example.restsecurity.transform.requset.like;

import lombok.Data;

@Data
public class LikeDeleteRequest {

    private int userId;

    private int postId;
}
