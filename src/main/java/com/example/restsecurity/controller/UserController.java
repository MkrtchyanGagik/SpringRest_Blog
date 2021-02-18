package com.example.restsecurity.controller;

import com.example.restsecurity.common.api.ApiResponse;
import com.example.restsecurity.service.user.UserService;
import com.example.restsecurity.transform.requset.user.UserCreateRequest;
import com.example.restsecurity.transform.requset.user.UserUpdateRequest;
import com.example.restsecurity.transform.response.user.UserCreateResponse;
import com.example.restsecurity.transform.response.user.UserGetResponse;
import com.example.restsecurity.transform.response.user.UserUpdateResponse;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<UserCreateResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return new ApiResponse<>("User successfully created", HttpStatus.OK.value(), userService.add(userCreateRequest));
    }

    @GetMapping("{id}")
    public ApiResponse<UserGetResponse> getUser(@PathVariable int id) {
        return new ApiResponse<UserGetResponse>(HttpStatus.CREATED.value(), userService.get(id));
    }

    @DeleteMapping("{id}")
    public ApiResponse<Void> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(String.format("USer by id: {%d} successfully removed", id), HttpStatus.OK.value());

    }

    @PutMapping("{id}")
    public ApiResponse<UserUpdateResponse> updateUser(@PathVariable int id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return new ApiResponse<>(String.format("USer by id: {%d} successfully updated", id), HttpStatus.OK.value(), userService.update(userUpdateRequest, id));
    }

    @GetMapping("/all")
    public ApiResponse<List<UserGetResponse>> getAllUsers() {

        return new ApiResponse<>("All users", HttpStatus.OK.value(), userService.getAll());
    }
}

