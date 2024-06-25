package by.modsen.practice.group11.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//@Schema(description = "OrderItem request")
public record OrderItemRequest(
        @NotNull(message = "Order ID is mandatory")
//        @Schema(description = "Order id")
        UUID orderId,

        @NotNull(message = "Product ID is mandatory")
//        @Schema(description = "Product id")
        UUID productId,

        @Min(value = 0, message = "Amount must be positive")
//        @Schema(description = "Amount", example = "52")
        int amount
) {
}
