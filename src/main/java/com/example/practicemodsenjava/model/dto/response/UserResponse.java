package com.example.practicemodsenjava.model.dto.response;

import java.util.UUID;
import java.time.LocalDateTime;
import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;

public record UserResponse(
        UUID id,
        String email,
        String login,
        String fullName,
        Gender gender,
        LocalDateTime birthday,
        Role role
) {}
