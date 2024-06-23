package by.modsen.practice.group11.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemRequest(
        @NotNull(message = "Order ID is mandatory")
        UUID orderId,

        @NotNull(message = "Product ID is mandatory")
        UUID productId,

        @Min(value = 0, message = "Amount must be positive")
        int amount
) {}
