package com.example.restsecurity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "post_id")
    private int postId;
}
