package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.ProductRequest;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.model.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CustomMapper.class)
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toProductResponseList(List<Product> productList);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryRefFromCategoryId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product toProduct(ProductRequest productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryRefFromCategoryId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product partialUpdate(ProductRequest productRequest, @MappingTarget Product product);
}
