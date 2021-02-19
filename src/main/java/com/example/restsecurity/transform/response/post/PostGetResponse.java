package com.example.restsecurity.transform.response.post;

import com.example.restsecurity.model.Category;
import com.example.restsecurity.model.Comment;
import com.example.restsecurity.transform.response.comment.CommentGetResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostGetResponse {


    private int id;

    private String tittle;

    private String postBody;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdate;

    private long viewCount;

    private long likeCount;

    private Category category;

    private List<CommentGetResponse> comments;

}
