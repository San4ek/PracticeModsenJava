package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.model.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getAmount()
        );
    }
}
