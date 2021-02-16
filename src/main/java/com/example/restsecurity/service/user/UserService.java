package com.example.restsecurity.service.user;

import com.example.restsecurity.common.exception.UserNotFoundException;
import com.example.restsecurity.model.User;
import com.example.restsecurity.common.enums.UserType;
import com.example.restsecurity.repository.UserRepository;
import com.example.restsecurity.service.AddSupported;
import com.example.restsecurity.service.DeleteSupported;
import com.example.restsecurity.service.GetSupported;
import com.example.restsecurity.service.UpdateSupported;
import com.example.restsecurity.transform.requset.user.UserCreateRequest;
import com.example.restsecurity.transform.requset.user.UserUpdateRequest;
import com.example.restsecurity.transform.response.user.UserCreateResponse;
import com.example.restsecurity.transform.response.user.UserGetResponse;
import com.example.restsecurity.transform.response.user.UserUpdateResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements AddSupported<UserCreateRequest,UserCreateResponse>, GetSupported<Integer, UserGetResponse>, DeleteSupported<Integer>, UpdateSupported<UserUpdateResponse, UserUpdateRequest,Integer> {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserCreateResponse add(UserCreateRequest userCreateRequest) {
        User user = new User();
        UserCreateResponse userCreateResponse=new UserCreateResponse();
        BeanUtils.copyProperties(userCreateRequest, user);
        user.setUserType(UserType.USER);
        User saved = userRepository.save(user);
        BeanUtils.copyProperties(saved, userCreateResponse);

        return userCreateResponse;
    }


    @Override
    public UserGetResponse get(Integer id) {
        boolean exist= userRepository.existsById(id);
        if (!exist){
            throw  new UserNotFoundException(id);
        }
       User user= userRepository.findById(id).get();
        UserGetResponse userGetResponse =new UserGetResponse();
        BeanUtils.copyProperties(user, userGetResponse);
        return userGetResponse;
    }

    @Override
    public List<UserGetResponse> getAll() {
        List<User> users=userRepository.findAll();
        List<UserGetResponse> userGetResponses=new ArrayList<>();
        for (User user:users) {
            UserGetResponse userGetResponse=new UserGetResponse();
            BeanUtils.copyProperties(user,userGetResponse);
            userGetResponses.add(userGetResponse);
        }
        BeanUtils.copyProperties(users,userGetResponses);
        return userGetResponses;
    }

    @Override
    public void delete(Integer id) {
        boolean exist= userRepository.existsById(id);
        if (!exist){
            throw  new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }


    @Override
    public UserUpdateResponse update(UserUpdateRequest userUpdateRequest, Integer id) {
            boolean exist= userRepository.existsById(id);
            if (!exist){
                throw  new UserNotFoundException(id);
            }
            User user=userRepository.findById(id).get();
            BeanUtils.copyProperties(userUpdateRequest,user);
            UserUpdateResponse userUpdateResponse=new UserUpdateResponse();
            BeanUtils.copyProperties(user, userUpdateResponse);
            userRepository.save(user);
            return userUpdateResponse;
        }

}
