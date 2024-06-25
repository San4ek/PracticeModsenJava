package by.modsen.practice.group11.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder
//@Schema(description = "Login request")
public record LoginRequest(
        @NotNull
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
        String password
) {
}
