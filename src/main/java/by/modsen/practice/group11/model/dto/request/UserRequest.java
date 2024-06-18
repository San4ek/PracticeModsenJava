package by.modsen.practice.group11.model.dto.request;

import by.modsen.practice.group11.model.enums.Gender;
import by.modsen.practice.group11.model.enums.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

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

        @NotNull
        @Size(max = 45, message = "Full name should not be longer than 45 characters")
        String fullName,

        @NotNull(message = "Gender is mandatory")
        Gender gender,

        @Past(message = "Birthday must be in the past")
        LocalDateTime birthday,

        @NotNull(message = "Role is mandatory")
        Role role
) {

}
