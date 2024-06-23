package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.mapper.UserMapper;
import com.example.practicemodsenjava.model.entity.User;
import com.example.practicemodsenjava.repository.UserRepository;
import com.example.practicemodsenjava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
        return userMapper.toUserResponseList(users);
    }

    @Override
    @Transactional
    public UserResponse createUser(User user) {
        userRepository.saveAndFlush(user);  // Используем saveAndFlush вместо save
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UUID userId, User userDetails) {
        User user = getUserOrThrow(userId);
        // Обновляем поля user с userDetails
        userRepository.saveAndFlush(user);  // Используем saveAndFlush вместо save
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }
}
