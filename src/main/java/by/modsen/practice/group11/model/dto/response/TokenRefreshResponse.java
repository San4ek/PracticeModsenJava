package by.modsen.practice.group11.model.dto.response;

import lombok.Builder;

@Builder
public record TokenRefreshResponse(
        String tokenAccess,
        String tokenRefresh,
        String tokenType
) {
}
