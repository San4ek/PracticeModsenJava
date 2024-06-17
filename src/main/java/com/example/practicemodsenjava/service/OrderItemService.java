package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {

    List<OrderItemResponse> getOrderItemsByOrderId(UUID orderId);

    OrderItemResponse createOrderItem(UUID orderId, UUID productId, int amount);

    void deleteOrderItem(UUID orderItemId);
}
