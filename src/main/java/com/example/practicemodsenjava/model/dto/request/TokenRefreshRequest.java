package com.example.practicemodsenjava.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TokenRefreshRequest(
        @NotBlank String refreshToken
) {
}
