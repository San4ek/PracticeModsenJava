package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.model.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    OrderItem toOrderItem(OrderItemRequest orderItem);

    List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> ordersItems);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem partialUpdate(OrderItemRequest categoryRequest, @MappingTarget OrderItem category);
}
