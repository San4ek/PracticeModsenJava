package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.enums.Gender;
import by.modsen.practice.group11.model.enums.Role;
import by.modsen.practice.group11.service.UserService;
import by.modsen.practice.group11.mapper.UserMapper;
import by.modsen.practice.group11.repository.UserRepository;
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
    public UserResponse createUser(String email, String login, String password, Role role) {
        User user = new User();
        // Set properties for user
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
    public UserResponse updateUser(UUID userId, String email, String login, Role role) {
        User user = getUserOrThrow(userId);
        // Update properties for user
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
                .orElseThrow(); // ToDo: Create exception
//                .orElseThrow(() -> new GlobalExceptionHandler("User with id " + userId + " not found"));
    }
}
