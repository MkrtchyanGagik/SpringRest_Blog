package com.example.restsecurity.controller;

import com.example.restsecurity.model.Post;
import com.example.restsecurity.service.post.PostService;
import com.example.restsecurity.transform.requset.post.PostCreateRequest;
import com.example.restsecurity.transform.requset.post.PostUpdateRequest;
import com.example.restsecurity.transform.response.post.PostCreateResponse;
import com.example.restsecurity.transform.response.post.PostGetResponse;
import com.example.restsecurity.transform.response.post.PostUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;

    }


    @PostMapping
    public PostCreateResponse createPost(@RequestBody PostCreateRequest postCreateRequest) {

        return postService.add(postCreateRequest);
    }


    @GetMapping("{id}")
    public PostGetResponse getPost(@PathVariable int id) {
        return postService.get(id);
    }

    @PutMapping("{id}")
    public PostUpdateResponse updatePost(@PathVariable int id, @RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.update(postUpdateRequest, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public List<Post> getPostByUserId(@PathVariable int userId) {
        return postService.getByUserId(userId);
    }
}
