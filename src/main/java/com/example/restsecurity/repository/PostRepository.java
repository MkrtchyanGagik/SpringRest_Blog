package com.example.restsecurity.repository;

import com.example.restsecurity.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUserId(Integer id);
}
