package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Mapper(uses = OrderItemMapper.class)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "order.id", target = "id")
    @Mapping(source = "order.user.id", target = "userId")
    @Mapping(source = "order.orderItems", target = "orderItems")
    OrderResponse toOrderResponse(Order order);
}