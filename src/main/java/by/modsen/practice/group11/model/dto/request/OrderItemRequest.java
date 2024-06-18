package by.modsen.practice.group11.model.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotNull(message = "Product ID is mandatory")
        UUID productId,

        @Min(value = 0, message = "Amount must be positive")
        int amount
) {}
