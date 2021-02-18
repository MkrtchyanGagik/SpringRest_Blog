package com.example.restsecurity.repository;

import com.example.restsecurity.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface CommentRepository extends JpaRepository<Comment,Integer> {
}
