package com.example.restsecurity.transform.response.user;

import com.example.restsecurity.model.Post;
import com.example.restsecurity.common.enums.UserType;
import lombok.Data;

import java.util.List;

@Data
public class UserGetResponse {

    private int id;

    private String name;


    private String surname;


    private String email;


    private UserType userType;




}
