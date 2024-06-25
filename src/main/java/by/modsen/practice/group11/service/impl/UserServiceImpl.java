package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.UserRequest;
import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.repository.UserRepository;
import by.modsen.practice.group11.service.UserService;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import by.modsen.practice.group11.service.mapper.PersonalInfoMapper;
import by.modsen.practice.group11.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PersonalInfoMapper personalInfoMapper;

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID userId) {

        return userMapper.toUserResponse(getUserOrThrow(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        return userMapper.toUserResponseList(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {

        return userMapper.toUserResponse(userRepository.save(userMapper.toUser(userRequest)));
    }

    @Override
    @Transactional
    public UserResponse updateUser(UUID userId, UserRequest userRequest) {

        User user = getUserOrThrow(userId);
        return userMapper.toUserResponse(userRepository.save(userMapper.partialUpdate(userRequest, user)));
    }


    @Override
    @Transactional
    public void deleteUser(UUID userId) {

        getUserOrThrow(userId);
        userRepository.deleteById(userId);
    }

    private User getUserOrThrow(UUID userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 71, "Can't find category by id = " + userId));
    }
}
