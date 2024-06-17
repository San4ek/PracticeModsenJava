package com.example.practicemodsenjava.model.dto.request;

import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record SignupRequest(
        @NotNull
        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        @Size(max = 50, message = "Email should not be longer than 50 characters")
        String email,

        @NotNull
        @NotBlank(message = "Login is mandatory")
        @Size(max = 20, message = "Login should not be longer than 20 characters")
        String login,

        @NotNull
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 300, message = "Password should be between 8 and 300 characters")
        String password,

        @NotNull
        @Size(max = 45, message = "Full name should not be longer than 45 characters")
        String fullName,

        @NotNull(message = "Gender is mandatory")
        Gender gender,

        @Past(message = "Birthday must be in the past")
        LocalDateTime birthday

) {
}
