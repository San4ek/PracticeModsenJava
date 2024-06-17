package com.example.practicemodsenjava.authenticationserver.model.dto.response;

import lombok.Builder;

@Builder
public record MessageResponse(
        String message
) {

}
