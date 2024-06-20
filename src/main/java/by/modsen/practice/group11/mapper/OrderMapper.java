package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.model.entity.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "personalInfoId", source = "personalInfo.id")
    OrderResponse toOrderResponse(Order order);
}