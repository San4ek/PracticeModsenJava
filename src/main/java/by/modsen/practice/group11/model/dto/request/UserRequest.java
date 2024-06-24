package by.modsen.practice.group11.model.dto.request;

import by.modsen.practice.group11.model.entity.PersonalInfo;
import by.modsen.practice.group11.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
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

        @NotNull(message = "Role is mandatory")
        Role role,

        @NotNull(message = "Personal info is mandatory")
        PersonalInfo personalInfoId
) { }
