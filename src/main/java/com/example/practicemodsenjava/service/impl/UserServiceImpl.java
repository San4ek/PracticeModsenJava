package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.exceptionHandling.GlobalExceptionHandler;
import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.mapper.UserMapper;
import com.example.practicemodsenjava.model.entity.User;
import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;
import com.example.practicemodsenjava.repository.UserRepository;
import com.example.practicemodsenjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID userId) {
        User user = getUserOrThrow(userId);
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse createUser(String email, String login, String password, String fullName, Gender gender, LocalDateTime birthday, Role role) {
        User user = new User();
        // Устанавливаем свойства для user
        // user.setEmail(email);
        // user.setLogin(login);
        // user.setPassword(password);
        // user.setFullName(fullName);
        // user.setGender(gender);
        // user.setBirthday(birthday);
        // user.setRole(role);

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UUID userId, String email, String login, String fullName, Gender gender, LocalDateTime birthday, Role role) {
        User user = getUserOrThrow(userId);
        // Обновляем свойства для user
        // user.setEmail(email);
        // user.setLogin(login);
        // user.setFullName(fullName);
        // user.setGender(gender);
        // user.setBirthday(birthday);
        // user.setRole(role);

        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User user = getUserOrThrow(userId);
        userRepository.delete(user);
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GlobalExceptionHandler("User with id " + userId + " not found"));
    }
}