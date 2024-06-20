package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.model.dto.request.UserRequest;
import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest.email(), userRequest.login(), userRequest.password(),
                userRequest.fullName(), userRequest.gender(), userRequest.birthday(), userRequest.role());
    }

    @PutMapping()
    public UserResponse updateUser(@RequestBody UserRequest userRequest){
        return userService.updateUser(userRequest.id(), userRequest.email(), userRequest.login(), userRequest.fullName(),
                userRequest.gender(), userRequest.birthday(), userRequest.role());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }

}
