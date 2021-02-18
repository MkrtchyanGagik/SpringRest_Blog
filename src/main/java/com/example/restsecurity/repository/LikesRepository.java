package com.example.restsecurity.repository;

import com.example.restsecurity.model.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Like,Integer> {

    public long countByPostId(int postId);

    public Like getByUserIdAndPostId(int userId,int PostId);

}
