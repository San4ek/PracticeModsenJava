package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.model.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CustomMapper.class)
public interface OrderItemMapper {
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    OrderItem toOrderItem(OrderItemRequest orderItem);

    List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> ordersItems);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "storyId", target = "story", qualifiedByName = "storyRefFromStoryId")
    OrderItem partialUpdate(OrderItemRequest categoryRequest, @MappingTarget OrderItem category);
}
