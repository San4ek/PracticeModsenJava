package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse getUserById(UUID userId);

    List<UserResponse> getAllUsers();

    UserResponse createUser(String email, String login, String password, String fullName, Gender gender, LocalDateTime birthday, Role role);

    UserResponse updateUser(UUID userId, String email, String login, String fullName, Gender gender, LocalDateTime birthday, Role role);

    void deleteUser(UUID userId);
}
