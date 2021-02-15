package com.example.restsecurity.transform.requset.user;

import com.example.restsecurity.common.enums.UserType;
import lombok.Data;

@Data
public class UserCreateRequest {

    private String name;


    private String surname;


    private String email;


    private String password;


    private UserType userType;




}
