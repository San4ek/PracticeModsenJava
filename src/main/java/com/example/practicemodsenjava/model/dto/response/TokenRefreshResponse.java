package com.example.practicemodsenjava.model.dto.response;

import lombok.Builder;

@Builder
public record TokenRefreshResponse(
        String accessToken,
        String refreshToken,
        String tokenType
) {
}
