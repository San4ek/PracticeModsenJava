package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.enums.Gender;
import by.modsen.practice.group11.model.enums.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse getUserById(UUID userId);

    List<UserResponse> getAllUsers();

    UserResponse createUser(String email, String login, String password, Role role);

    UserResponse updateUser(UUID userId, String email, String login, Role role);

    void deleteUser(UUID userId);
}
