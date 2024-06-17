package com.example.practicemodsenjava.model.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record LoginRequest(
        String email,
        String login,
        String password
) {

}
