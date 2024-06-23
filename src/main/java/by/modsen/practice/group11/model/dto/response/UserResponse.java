package by.modsen.practice.group11.model.dto.response;

import by.modsen.practice.group11.model.enums.Role;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String login,
        Role role,
        PersonalInfoResponse personalInfoResponse
) {}
