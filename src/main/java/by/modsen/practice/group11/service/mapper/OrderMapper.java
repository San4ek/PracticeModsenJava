package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.model.entity.Order;
import org.mapstruct.*;

import java.util.List;

// ToDo: Change mapper

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "personalInfoId", source = "personalInfo.id")
    OrderResponse toOrderResponse(Order orderItem);

    List<OrderItemResponse> toOrderResponseList(List<Order> ordersItems);

    @Mapping(source = "orderId", target = "order", qualifiedByName = "orderRefFromOrderId")
    Order toOrder(OrderRequest orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "orderId", target = "order", qualifiedByName = "orderRefFromOrderId")
    Order partialUpdate(OrderRequest categoryRequest, @MappingTarget Order category);
}

