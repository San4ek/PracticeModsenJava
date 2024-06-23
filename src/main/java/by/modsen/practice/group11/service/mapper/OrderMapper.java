package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.model.entity.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

// ToDo: Change mapper

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "personalInfoId", source = "personalInfo.id")
    OrderResponse toOrderResponse(Order order);
    Order toOrder(OrderRequest orderRequest);
}
