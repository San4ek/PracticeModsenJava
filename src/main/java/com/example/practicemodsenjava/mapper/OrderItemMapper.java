package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(source = "orderItem.id", target = "id")
    @Mapping(source = "orderItem.product.id", target = "productId")
    @Mapping(source = "orderItem.amount", target = "amount")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}