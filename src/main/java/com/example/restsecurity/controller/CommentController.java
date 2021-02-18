package com.example.restsecurity.controller;

import com.example.restsecurity.service.comment.CommentService;
import com.example.restsecurity.transform.requset.comment.CommentCreateRequest;
import com.example.restsecurity.transform.requset.comment.CommentUpdateRequest;
import com.example.restsecurity.transform.response.comment.CommentCreateResponse;
import com.example.restsecurity.transform.response.comment.CommentUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController (CommentService commentService){
        this.commentService=commentService;

    }

    @PostMapping
    public CommentCreateResponse createComment(@RequestBody CommentCreateRequest commentCreateRequest){

        return commentService.add(commentCreateRequest);
    }
@PutMapping("{id}")
    public CommentUpdateResponse updateComment(@PathVariable int id, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.update(commentUpdateRequest,id);

}
@DeleteMapping("{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id){
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

}
}
