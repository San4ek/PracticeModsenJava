package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.model.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse getOrderById(UUID orderId);
    List<OrderResponse> getAllOrders();
    OrderResponse createOrder(Order order);
    OrderResponse updateOrder(UUID orderId, Order orderDetails);
    void deleteOrder(UUID orderId);

    List<OrderResponse> getOrdersByUserId(UUID userId); // Новый метод
}
