package by.modsen.practice.group11.model.dto.request;

import by.modsen.practice.group11.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
//@Schema(description = "SigUp request")
public record SignUpRequest(
        @NotNull
        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        @Size(max = 50, message = "Email should not be longer than 50 characters")
//        @Schema(description = "Email", example = "example@gmail.com")
        String email,

        @NotNull
        @NotBlank(message = "Login is mandatory")
        @Size(max = 20, message = "Login should not be longer than 20 characters")
//        @Schema(description = "Login", example = "Simon")
        String login,

        @NotNull
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 300, message = "Password should be between 8 and 300 characters")
//        @Schema(description = "Password", example = "my_secret_password")
        String password,

        @NotNull
        @Size(max = 45, message = "Full name should not be longer than 45 characters")
//        @Schema(description = "Full name", example = "Simon Zhuk")
        String fullName,

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Gender is mandatory")
//        @Schema(description = "Gender, it should be written with a capital letter", example = "MALE")
        Gender gender,

        @JsonFormat(pattern="dd-MM-yyyy")
        @Past(message = "Birthday must be in the past")
//        @Schema(description = "Your birthday, it should be written in this format dd:mm:yy", example = "23:08:2023")
        LocalDate birthday
) {
}
