package by.modsen.practice.group11.model.dto.response;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        UUID categoryId,
        String name
) {}
