package com.example.practicemodsenjava.model.dto.response;

import java.util.List;

public record OrderListResponse(
        List<OrderResponse> orders
) {}