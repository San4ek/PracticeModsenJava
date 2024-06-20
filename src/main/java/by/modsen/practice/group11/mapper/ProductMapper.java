package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toProductResponse(Product product);

}
