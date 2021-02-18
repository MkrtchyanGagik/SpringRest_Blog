package com.example.restsecurity.service.comment;

import com.example.restsecurity.common.exception.CommentNotFoundException;
import com.example.restsecurity.common.exception.PostNotFoundException;
import com.example.restsecurity.common.exception.UserNotFoundException;
import com.example.restsecurity.model.Comment;
import com.example.restsecurity.model.Post;
import com.example.restsecurity.model.User;
import com.example.restsecurity.repository.CommentRepository;
import com.example.restsecurity.repository.PostRepository;
import com.example.restsecurity.repository.UserRepository;
import com.example.restsecurity.service.AddSupported;
import com.example.restsecurity.service.DeleteSupported;
import com.example.restsecurity.service.UpdateSupported;
import com.example.restsecurity.transform.requset.comment.CommentCreateRequest;
import com.example.restsecurity.transform.requset.comment.CommentUpdateRequest;
import com.example.restsecurity.transform.response.comment.CommentCreateResponse;
import com.example.restsecurity.transform.response.comment.CommentUpdateResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class CommentService implements AddSupported<CommentCreateRequest, CommentCreateResponse>, UpdateSupported<CommentUpdateResponse, CommentUpdateRequest, Integer>, DeleteSupported<Integer> {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public CommentCreateResponse add(CommentCreateRequest commentCreateRequest) {
        boolean existUser = userRepository.findById(commentCreateRequest.getUserId()).isPresent();
        boolean existPost = postRepository.findById(commentCreateRequest.getPostId()).isPresent();

        if (!existPost) {
            throw new PostNotFoundException(commentCreateRequest.getPostId());
        }
        if (!existUser) {
            throw new UserNotFoundException(commentCreateRequest.getUserId());
        }
        User user = userRepository.findById(commentCreateRequest.getUserId()).get();

        Post post = postRepository.findById(commentCreateRequest.getPostId()).get();

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setPost(post);
        comment.setCommentBody(commentCreateRequest.getCommentBody());
        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        CommentCreateResponse commentCreateResponse = new CommentCreateResponse();
        BeanUtils.copyProperties(savedComment, commentCreateResponse);
        return commentCreateResponse;
    }


    @Override
    public CommentUpdateResponse update(CommentUpdateRequest commentUpdateRequest, Integer id) {
        boolean exist = commentRepository.findById(id).isPresent();
        if (!exist) {
            throw new CommentNotFoundException(id);
        }
        Comment comment = commentRepository.findById(id).get();
        BeanUtils.copyProperties(commentUpdateRequest, comment);
        Comment updatedComment=commentRepository.save(comment);
        CommentUpdateResponse commentUpdateResponse=new CommentUpdateResponse();
        BeanUtils.copyProperties(updatedComment,commentUpdateResponse);
        return commentUpdateResponse;
    }


    @Override
    public void delete(Integer id) {
        boolean exist=commentRepository.findById(id).isPresent();
        if (!exist){
            throw new CommentNotFoundException(id);
        }
        commentRepository.deleteById(id);
    }



}

