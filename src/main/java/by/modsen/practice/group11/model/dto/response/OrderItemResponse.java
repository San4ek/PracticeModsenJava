package by.modsen.practice.group11.model.dto.response;

import java.util.UUID;

public record OrderItemResponse(
        UUID id,
        UUID productId,
        int amount
) {}
