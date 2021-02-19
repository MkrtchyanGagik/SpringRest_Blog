package com.example.restsecurity.service.like;

import com.example.restsecurity.common.exception.PostNotFoundException;
import com.example.restsecurity.common.exception.UserNotFoundException;
import com.example.restsecurity.model.Like;
import com.example.restsecurity.repository.LikesRepository;
import com.example.restsecurity.repository.PostRepository;
import com.example.restsecurity.repository.UserRepository;
import com.example.restsecurity.service.AddSupported;
import com.example.restsecurity.service.DeleteSupported;
import com.example.restsecurity.transform.requset.like.LikeAddRequest;
import com.example.restsecurity.transform.requset.like.LikeDeleteRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements AddSupported<LikeAddRequest, Long>, DeleteSupported<LikeDeleteRequest> {
    private final LikesRepository likesRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(LikesRepository likesRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likesRepository = likesRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Long add(LikeAddRequest likeAddRequest) {
        boolean existPost = postRepository.findById(likeAddRequest.getPostId()).isPresent();
        boolean existUser = userRepository.findById(likeAddRequest.getUserId()).isPresent();
        if (!existPost) {
            throw new PostNotFoundException(likeAddRequest.getPostId());
        }

        if (!existUser) {
            throw new UserNotFoundException(likeAddRequest.getPostId());
        }
        if (likesRepository.getByUserIdAndPostId(likeAddRequest.getUserId(), likeAddRequest.getPostId()) == null) {

            Like like = new Like();
            BeanUtils.copyProperties(likeAddRequest, like);
            Like addedLike = likesRepository.save(like);
            return likesRepository.countByPostId(addedLike.getPostId());
        }
        return likesRepository.countByPostId(likeAddRequest.getPostId());
    }


    @Override
    public void delete(LikeDeleteRequest likeDeleteRequest) {
        Like like = likesRepository.getByUserIdAndPostId(likeDeleteRequest.getUserId(), likeDeleteRequest.getPostId());
        likesRepository.deleteById(like.getId());
    }

}

