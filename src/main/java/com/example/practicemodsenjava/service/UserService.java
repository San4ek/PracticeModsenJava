package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    void deleteUser(UUID id);

    UserDTO getUserById(UUID id);

    List<UserDTO> getAllUsers();
}