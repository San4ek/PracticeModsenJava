package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.model.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CustomMapper.class)
public interface OrderItemMapper {
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "orderId", source = "order.id")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> ordersItems);

    @Mapping(source = "orderId", target = "order", qualifiedByName = "orderRefFromOrderId")
    @Mapping(source = "productId", target = "product", qualifiedByName = "productRefFromProductId")
    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "orderId", target = "order", qualifiedByName = "orderRefFromOrderId")
    @Mapping(source = "productId", target = "product", qualifiedByName = "productRefFromProductId")
    OrderItem partialUpdate(OrderItemRequest orderItemRequest, @MappingTarget OrderItem orderItem);
}
