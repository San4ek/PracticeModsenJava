package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.exceptionHandling.GlobalExceptionHandler;
import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.mapper.OrderItemMapper;
import com.example.practicemodsenjava.model.entity.OrderItem;
import com.example.practicemodsenjava.repository.OrderItemRepository;
import com.example.practicemodsenjava.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> getOrderItemsByOrderId(UUID orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);
        return orderItems.stream()
                .map(orderItemMapper::toOrderItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderItemResponse createOrderItem(UUID orderId, UUID productId, int amount) {
        OrderItem orderItem = new OrderItem();
        // Устанавливаем свойства для orderItem
        // orderItem.setOrder(...);
        // orderItem.setProduct(...);
        // orderItem.setAmount(amount);

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemResponse(savedOrderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(UUID orderItemId) {
        OrderItem orderItem = getOrderItemOrThrow(orderItemId);
        orderItemRepository.delete(orderItem);
    }

    private OrderItem getOrderItemOrThrow(UUID orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new GlobalExceptionHandler("OrderItem with id " + orderItemId + " not found"));
    }
}