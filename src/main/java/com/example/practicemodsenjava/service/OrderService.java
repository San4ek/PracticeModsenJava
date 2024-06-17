package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.request.OrderItemRequest;
import com.example.practicemodsenjava.model.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse getOrderById(UUID orderId);

    List<OrderResponse> getOrdersByUserId(UUID userId);

    OrderResponse createOrder(UUID userId, List<OrderItemRequest> orderItems);

    void deleteOrder(UUID orderId);
}
