package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> orderItems);  // Новый метод
}
