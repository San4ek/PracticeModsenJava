package by.modsen.practice.group11.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderRequest(
        @NotNull(message = "Order id is mandatory")
        UUID userId
) {}
