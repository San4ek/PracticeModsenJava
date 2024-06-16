package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.model.entity.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUser().getId(),
                order.getOrderItems().stream()
                        .map(orderItemMapper::toOrderItemResponse)
                        .collect(Collectors.toList())
        );
    }
}
