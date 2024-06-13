package com.example.practicemodsenjava.dto;

import java.util.UUID;
import java.time.LocalDateTime;
import com.example.practicemodsenjava.model.enums.Gender;
import com.example.practicemodsenjava.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private UUID id;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @NotNull
    @Size(max = 20, message = "Email should not be longer than 20 characters")
    private String email;

    @NotBlank(message = "Login is mandatory")
    @NotNull
    @Size(min = 4, max = 20, message = "Login should be between 4 and 20 characters")
    private String login;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 300, message = "Password should be between 8 and 300 characters")
    private String password;

    @NotNull
    @Size(max = 45, message = "Full name should not be longer than 45 characters")
    private String fullName;

    @NotNull
    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    private LocalDateTime birthday;

    @NotNull(message = "Role is mandatory")
    private Role role;
}
