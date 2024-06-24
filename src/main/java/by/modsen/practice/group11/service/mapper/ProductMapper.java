package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.ProductRequest;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.model.entity.Product;
import org.mapstruct.*;

import java.util.List;

// ToDo: Change mapper

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CustomMapper.class})
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toProductResponse(Product product);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryRefFromCategoryId")
    Product toProduct(ProductRequest productRequest);

    List<ProductResponse> toProductResponseList(List<Product> productList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductRequest productRequest, @MappingTarget Product product);
}
