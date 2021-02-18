package com.example.restsecurity.transform.response.comment;

import com.example.restsecurity.model.User;
import lombok.Data;

@Data
public class CommentGetResponse {
    
    
    private String commentBody;

    private User user;
    
}
