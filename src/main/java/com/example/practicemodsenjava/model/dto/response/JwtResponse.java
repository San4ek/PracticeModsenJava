package com.example.practicemodsenjava.model.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record JwtResponse(
        String token,
        String type,
        String refreshToken,
        Long id,
        String login,
        String email,
        List<String> roles
) {

}
