package com.example.practicemodsenjava.model.dto.response;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        UUID categoryId,
        String name
) {}
