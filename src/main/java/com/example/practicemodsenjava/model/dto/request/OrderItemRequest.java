package com.example.practicemodsenjava.model.dto.request;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotNull(message = "Product ID is mandatory")
        UUID productId,

        @Positive(message = "Amount must be positive")
        int amount
) {}
