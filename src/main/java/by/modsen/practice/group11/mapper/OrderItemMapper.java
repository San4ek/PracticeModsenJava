package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.entity.OrderItem;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
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
