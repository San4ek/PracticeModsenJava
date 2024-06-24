package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.request.OrderRequest;
import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toOrderResponseList(List<Order> orders);  // Новый метод

    Order toOrder(OrderRequest orderRequest);
}
