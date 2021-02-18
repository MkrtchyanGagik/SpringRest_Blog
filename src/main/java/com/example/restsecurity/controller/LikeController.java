package com.example.restsecurity.controller;

import com.example.restsecurity.service.like.LikeService;
import com.example.restsecurity.transform.requset.like.LikeAddRequest;
import com.example.restsecurity.transform.requset.like.LikeDeleteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public Long addLike(@RequestBody LikeAddRequest likeAddRequest) {
        return likeService.add(likeAddRequest);
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> deleteLike(@RequestBody LikeDeleteRequest likeDeleteRequest) {
        likeService.delete(likeDeleteRequest);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
