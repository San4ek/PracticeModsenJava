package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.entity.Order;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
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
                order.getPersonalInfo().getId(),
                order.getOrderItems().stream()
                        .map(orderItemMapper::toOrderItemResponse)
                        .collect(Collectors.toList())
        );
    }
}
