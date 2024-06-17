package com.example.practicemodsenjava.model.dto.response;

import lombok.Builder;

import java.util.List;

// TODO add refresh token
@Builder
public record JwtResponse(
        String token
//        long expiresIn,
//        String refreshToken
) {}
