package com.example.practicemodsenjava.model.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record UserRequest(

        UUID id,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        @NotNull
        @Size(max = 50, message = "Email should not be longer than 50 characters")
        String email,

        @NotBlank(message = "Login is mandatory")
        @NotNull
        @Size(max = 20, message = "Login should not be longer than 20 characters")
        String login,

        @NotBlank(message = "Password is mandatory")
        @NotNull
        @Size(min = 8, max = 300, message = "Password should be between 8 and 300 characters")
        String password,

        @NotNull
        @Size(max = 45, message = "Full name should not be longer than 45 characters")
        String fullName,

        @NotNull(message = "Gender is mandatory")
        Gender gender,

        @Past(message = "Birthday must be in the past")
        LocalDateTime birthday,

        @NotNull(message = "Role is mandatory")
        Role role
) {}
