package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.ProductResponse;
import com.example.practicemodsenjava.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "product.id", target = "id")
    @Mapping(source = "product.category.id", target = "categoryId")
    ProductResponse toProductResponse(Product product);
}