package com.example.practicemodsenjava.model.dto.response;

import java.util.List;

public record OrderItemListResponse(
        List<OrderItemResponse> orderItems
) {}