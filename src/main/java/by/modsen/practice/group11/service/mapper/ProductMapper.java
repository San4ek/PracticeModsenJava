package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.ProductRequest;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

// ToDo: Change mapper

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toProductResponse(Product product);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryRefFromCategoryId")
    Product toProduct(ProductRequest productRequest);
}
