package com.example.practicemodsenjava.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {

    private UUID id;

    @NotNull(message = "User ID is mandatory")
    private UUID userId;

    @NotNull(message = "Order items are mandatory")
    private List<OrderItemDTO> orderItems;
}