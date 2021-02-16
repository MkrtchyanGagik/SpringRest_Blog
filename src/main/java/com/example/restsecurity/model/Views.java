package com.example.restsecurity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "views", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"})})
@Data
public class Views {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="user_id",unique = true)
    private int userId;
    @Column(name = "post_id")
    private int postId;
}
