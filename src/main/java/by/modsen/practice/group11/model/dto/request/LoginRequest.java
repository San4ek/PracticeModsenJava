package by.modsen.practice.group11.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginRequest(
        @NotNull
        @NotBlank(message = "Login is mandatory")
        @Size(min = 2, max = 50, message = "Login should be in range [2, 50]")
        String login,

        @NotNull
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 300, message = "Password should be between 8 and 300 characters")
        String password
) {
}
