package com.example.practicemodsenjava.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class OrderItemDTO {

    private UUID id;

    @NotNull(message = "Order ID is mandatory")
    private UUID orderId;

    @NotNull(message = "Product ID is mandatory")
    private UUID productId;

}