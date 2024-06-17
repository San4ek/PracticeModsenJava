package com.example.practicemodsenjava.authenticationserver.model.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record JwtResponse(
        String token,
        String type,
        Long id,
        String username,
        String email,
        List<String> roles
) {

}
