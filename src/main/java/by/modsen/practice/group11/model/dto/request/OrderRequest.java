package by.modsen.practice.group11.model.dto.request;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull(message = "User ID is mandatory")
        UUID userId,

        @NotEmpty(message = "Order items are mandatory")
        @Valid
        List<OrderItemRequest> orderItems
) {}
