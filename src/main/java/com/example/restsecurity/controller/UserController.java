package com.example.restsecurity.controller;

import com.example.restsecurity.model.User;
import com.example.restsecurity.service.user.UserService;
import com.example.restsecurity.transform.requset.user.UserCreateRequest;
import com.example.restsecurity.transform.requset.user.UserUpdateRequest;
import com.example.restsecurity.transform.response.user.UserCreateResponse;
import com.example.restsecurity.transform.response.user.UserGetResponse;
import com.example.restsecurity.transform.response.user.UserUpdateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.add(userCreateRequest);
    }

    @GetMapping("{id}")
    public UserGetResponse getUser(@PathVariable int id) {
        return userService.get(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("{id}")
    public UserUpdateResponse updateUser(@PathVariable int id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest, id);
    }

    @GetMapping("/all")
    public List<UserGetResponse> getAllUsers() {

        return userService.getAll();
    }
}

