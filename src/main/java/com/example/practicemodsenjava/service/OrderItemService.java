package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.dto.OrderItemDTO;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {

    OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO);

    OrderItemDTO updateOrderItem(UUID id, OrderItemDTO orderItemDTO);

    void deleteOrderItem(UUID id);

    OrderItemDTO getOrderItemById(UUID id);

    List<OrderItemDTO> getOrderItemsByOrderId(UUID orderId);
}