package by.modsen.practice.group11.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TokenRefreshRequest(
        @NotBlank String refreshToken
) {
}
