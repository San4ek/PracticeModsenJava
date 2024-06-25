package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.model.entity.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CustomMapper.class)
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toOrderResponseList(List<Order> orders);

    @Mapping(target = "user", source = "userId", qualifiedByName = "userRefFromUserId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toOrder(OrderRequest orderRequest);

    @Mapping(target = "user", source = "id", qualifiedByName = "userRefFromUserId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toOrder(UserJwt userJwt);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", source = "userId", qualifiedByName = "userRefFromUserId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order partialUpdate(OrderRequest orderRequest, @MappingTarget Order order);
}

