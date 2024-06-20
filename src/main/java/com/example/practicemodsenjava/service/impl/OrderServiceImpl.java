package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.mapper.OrderMapper;
import com.example.practicemodsenjava.model.entity.Order;
import com.example.practicemodsenjava.repository.OrderRepository;
import com.example.practicemodsenjava.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(UUID orderId) {
        Order order = getOrderOrThrow(orderId);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toOrderResponseList(orders);
    }

    @Override
    @Transactional
    public OrderResponse createOrder(Order order) {
        orderRepository.saveAndFlush(order);  // Используем saveAndFlush вместо save
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(UUID orderId, Order orderDetails) {
        Order order = getOrderOrThrow(orderId);
        // Обновляем поля order с orderDetails
        orderRepository.saveAndFlush(order);  // Используем saveAndFlush вместо save
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(UUID userId) {
        List<Order> orders = orderRepository.findByUser_Id(userId);
        return orderMapper.toOrderResponseList(orders);
    }

    private Order getOrderOrThrow(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow();
    }
}
