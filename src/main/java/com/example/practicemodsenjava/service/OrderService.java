package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(UUID id, OrderDTO orderDTO);

    void deleteOrder(UUID id);

    OrderDTO getOrderById(UUID id);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByUserId(UUID userId);
}