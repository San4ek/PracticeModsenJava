package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.model.dto.request.OrderItemRequest;
import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.mapper.OrderMapper;
import com.example.practicemodsenjava.model.entity.Order;
import com.example.practicemodsenjava.repository.OrderRepository;
import com.example.practicemodsenjava.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.practicemodsenjava.exceptionHandling.GlobalExceptionHandler;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(UUID orderId) {
        Order order = getOrderOrThrow(orderId);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(UUID userId) {
        List<Order> orders = orderRepository.findByUser_Id(userId);
        return orders.stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponse createOrder(UUID userId, List<OrderItemRequest> orderItems) {
        Order order = new Order();
        // Set properties for order
        // order.setUser(...);
        // order.setOrderItems(...);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {
        Order order = getOrderOrThrow(orderId);
        orderRepository.delete(order);
    }

    private Order getOrderOrThrow(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalExceptionHandler("Order with id " + orderId + " not found"));
    }
}
