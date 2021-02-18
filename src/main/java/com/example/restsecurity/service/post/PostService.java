package com.example.restsecurity.service.post;

import com.example.restsecurity.common.exception.CategoryNotFoundException;
import com.example.restsecurity.common.exception.PostNotFoundException;
import com.example.restsecurity.common.exception.UserNotFoundException;
import com.example.restsecurity.model.Category;
import com.example.restsecurity.model.Post;
import com.example.restsecurity.model.User;
import com.example.restsecurity.repository.CategoryRepository;
import com.example.restsecurity.repository.PostRepository;
import com.example.restsecurity.repository.UserRepository;
import com.example.restsecurity.service.AddSupported;
import com.example.restsecurity.service.DeleteSupported;
import com.example.restsecurity.service.GetSupported;
import com.example.restsecurity.service.UpdateSupported;
import com.example.restsecurity.transform.requset.post.PostCreateRequest;
import com.example.restsecurity.transform.requset.post.PostUpdateRequest;
import com.example.restsecurity.transform.response.post.PostCreateResponse;
import com.example.restsecurity.transform.response.post.PostGetResponse;
import com.example.restsecurity.transform.response.post.PostUpdateResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements AddSupported<PostCreateRequest, PostCreateResponse>, GetSupported<Integer, PostGetResponse>, UpdateSupported<PostUpdateResponse, PostUpdateRequest, Integer>, DeleteSupported<Integer> {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private CategoryRepository categoryRepository;


    public PostService(UserRepository userRepository, CategoryRepository categoryRepository, PostRepository postRepository) {
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;

    }


    @Override
    public PostCreateResponse add(PostCreateRequest postCreateRequest) {
        Boolean existUser = userRepository.findById(postCreateRequest.getUserId()).isPresent();
        Boolean existCategory = categoryRepository.findById(postCreateRequest.getCategoryId()).isPresent();
        if (!existUser) {
            throw new UserNotFoundException(postCreateRequest.getUserId());
        }
        if (!existCategory) {
            throw new CategoryNotFoundException(postCreateRequest.getCategoryId());
        }
        User user = userRepository.findById(postCreateRequest.getUserId()).get();
        Category category = categoryRepository.findById(postCreateRequest.getCategoryId()).get();
        Post post = new Post();
        BeanUtils.copyProperties(postCreateRequest, post);
        post.setCategory(category);
        post.setUser(user);
        post.setCreatedDate(LocalDateTime.now());
        post.setLastUpdate(LocalDateTime.now());
        Post savedPost = postRepository.save(post);
        PostCreateResponse postCreateResponse = new PostCreateResponse();
        BeanUtils.copyProperties(savedPost, postCreateResponse);
        return postCreateResponse;
    }


    @Override
    public PostGetResponse get(Integer id) {
        boolean exist = postRepository.findById(id).isPresent();

        if (!exist) {
            throw new PostNotFoundException(id);
        }

        Post post = postRepository.findById(id).get();
        PostGetResponse postGetResponse = new PostGetResponse();
        BeanUtils.copyProperties(post, postGetResponse);
        return postGetResponse;
    }

    @Override
    public List<PostGetResponse> getAll() {
        List<Post> posts = postRepository.findAll();
        List<PostGetResponse> postGetResponses = new ArrayList<>();

        for (Post post : posts) {
            PostGetResponse postGetResponse = new PostGetResponse();
            BeanUtils.copyProperties(post, postGetResponse);
            postGetResponses.add(postGetResponse);
        }

        return postGetResponses;
    }

    public List<PostGetResponse> getAllByUserId(Integer id) {
        List<Post> userPosts = postRepository.findAllByUserId(id);
        List<PostGetResponse> userPostGetResponses = new ArrayList<>();

        for (Post post : userPosts) {
            PostGetResponse postGetResponse = new PostGetResponse();
            BeanUtils.copyProperties(post, postGetResponse);
            userPostGetResponses.add(postGetResponse);
        }
        return userPostGetResponses;
    }

    @Override
    public PostUpdateResponse update(PostUpdateRequest postUpdateRequest, Integer id) {
        boolean exist = postRepository.findById(id).isPresent();

        if (!exist) {
            throw new PostNotFoundException(id);
        }
        Post post = postRepository.findById(id).get();
        BeanUtils.copyProperties(postUpdateRequest, post);
        PostUpdateResponse postUpdateResponse = new PostUpdateResponse();
        BeanUtils.copyProperties(post, postUpdateResponse);
        return postUpdateResponse;
    }

    @Override
    public void delete(Integer id) {
        boolean exist = postRepository.findById(id).isPresent();

        if (!exist) {
            throw new PostNotFoundException(id);
        }
        postRepository.deleteById(id);
    }
}
