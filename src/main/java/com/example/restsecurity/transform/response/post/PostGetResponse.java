package com.example.restsecurity.transform.response.post;

import com.example.restsecurity.model.Category;
import com.example.restsecurity.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class PostGetResponse {





    private int id;

    private String tittle;

    private String postBody;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdate;

    private long viewCount;

    private long likeCount;

    User user;

    Category category;


}
