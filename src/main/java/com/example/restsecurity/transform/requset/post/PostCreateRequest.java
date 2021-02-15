package com.example.restsecurity.transform.requset.post;

import lombok.Data;

@Data
public class PostCreateRequest {



    private String tittle;

    private String postBody;
    
    private int userId;

    private int categoryId;

}
