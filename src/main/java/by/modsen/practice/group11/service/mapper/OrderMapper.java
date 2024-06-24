package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.model.entity.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CustomMapper.class)
public interface OrderMapper {

    @Mapping(target = "personalInfoId", source = "personalInfo.id")
    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toOrderResponseList(List<Order> orders);

    @Mapping(source = "personalInfoId", target = "personalInfo", qualifiedByName = "personalInfoRefFromPersonalInfoId")
    Order toOrder(OrderRequest orderRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "personalInfoId", target = "personalInfo", qualifiedByName = "personalInfoRefFromPersonalInfoId")
    Order partialUpdate(OrderRequest orderRequest, @MappingTarget Order order);
}

