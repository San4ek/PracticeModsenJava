package com.example.practicemodsenjava.model.dto.response;

import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID userId,
        List<OrderItemResponse> orderItems
) {}