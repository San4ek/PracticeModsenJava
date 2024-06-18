package by.modsen.practice.group11.model.dto.response;

import by.modsen.practice.group11.model.enums.Gender;
import by.modsen.practice.group11.model.enums.Role;

import java.util.UUID;
import java.time.LocalDateTime;

public record UserResponse(
        UUID id,
        String email,
        String login,
        Role role
) {}
