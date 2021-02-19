package com.example.restsecurity.controller;

import com.example.restsecurity.common.api.ApiResponse;
import com.example.restsecurity.service.post.PostService;
import com.example.restsecurity.transform.requset.post.PostCreateRequest;
import com.example.restsecurity.transform.requset.post.PostUpdateRequest;
import com.example.restsecurity.transform.response.post.PostCreateResponse;
import com.example.restsecurity.transform.response.post.PostGetResponse;
import com.example.restsecurity.transform.response.post.PostUpdateResponse;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<PostGetResponse> getPost(@PathVariable int id) {
        return new ApiResponse<>(HttpStatus.OK.value(),postService.get(id));
    }

    @PutMapping("{id}")
    public ApiResponse<PostUpdateResponse>updatePost(@PathVariable int id, @RequestBody PostUpdateRequest postUpdateRequest) {
        return new ApiResponse<PostUpdateResponse>(String.format("post by id:{%d} updated" ,id),HttpStatus.OK.value(),postService.update(postUpdateRequest,id));
    }

    @DeleteMapping("{id}")
    public ApiResponse<Void> deletePost(@PathVariable int id) {
        postService.delete(id);
        return new ApiResponse<Void>(String.format("Post by id:{%d} is successfully deleted",id),HttpStatus.OK.value());
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<PostGetResponse>> getPostByUserId(@PathVariable Integer userId) {
        return new ApiResponse<List<PostGetResponse>>(String.format("All post's for appropriate {%d}",userId),HttpStatus.OK.value(),postService.getAllByUserId(userId));
    }

    @GetMapping("/all")
    public List<PostGetResponse> getAllPosts() {
        return postService.getAll();
    }
}
