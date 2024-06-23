package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.service.mapper.PersonalInfoMapper;
import by.modsen.practice.group11.service.mapper.UserMapper;
import by.modsen.practice.group11.model.dto.request.UserRequest;
import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.repository.UserRepository;
import by.modsen.practice.group11.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// ToDo: Change service methods

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PersonalInfoMapper personalInfoMapper;

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
    public UserResponse createUser(UserRequest userRequest) {
        User savedUser = userRepository.save(userMapper.toUser(userRequest));
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UUID userId, UserRequest userRequest) {
        User user = getUserOrThrow(userId);
        user.setEmail(userRequest.email());
        user.setRole(userRequest.role());
        user.setLogin(userRequest.login());
        user.setPassword(userRequest.password());
        user.setPersonalInfo(personalInfoMapper.toPersonalInfo(userRequest.personalInfoRequest()));
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
                .orElseThrow(); // ToDo: Create exception
//                .orElseThrow(() -> new GlobalExceptionHandler("User with id " + userId + " not found"));
    }
}
