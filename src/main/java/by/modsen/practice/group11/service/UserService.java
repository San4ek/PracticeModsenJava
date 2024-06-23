package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.UserRequest;
import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.enums.Gender;
import by.modsen.practice.group11.model.enums.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse getUserById(UUID userId);

    List<UserResponse> getAllUsers();

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(UUID userId, UserRequest userRequest);

    void deleteUser(UUID userId);
}
