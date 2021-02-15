package com.example.restsecurity.transform.response.post;

import com.example.restsecurity.model.Category;
import com.example.restsecurity.model.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PostCreateResponse {



    private int id;

    private String tittle;

    private String postBody;

    private LocalDateTime createdDate;

    Category category;
}
