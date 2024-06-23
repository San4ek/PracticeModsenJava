package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse getUserById(UUID userId);
    List<UserResponse> getAllUsers();
    UserResponse createUser(User user);
    UserResponse updateUser(UUID userId, User userDetails);
    void deleteUser(UUID userId);
}
