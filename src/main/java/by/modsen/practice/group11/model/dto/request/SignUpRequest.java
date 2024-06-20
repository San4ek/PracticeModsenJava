package by.modsen.practice.group11.model.dto.request;

import by.modsen.practice.group11.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SignUpRequest(
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

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Gender is mandatory")
        Gender gender,

        @JsonFormat(pattern="dd-MM-yyyy")
        @Past(message = "Birthday must be in the past")
        LocalDate birthday
) {
}
