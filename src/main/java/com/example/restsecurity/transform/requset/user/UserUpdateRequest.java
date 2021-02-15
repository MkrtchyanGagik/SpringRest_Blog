package com.example.restsecurity.transform.requset.user;

import com.example.restsecurity.common.enums.UserType;
import lombok.Data;

@Data
public class UserUpdateRequest {


    private String name;

    private String surname;

    private String password;


}
