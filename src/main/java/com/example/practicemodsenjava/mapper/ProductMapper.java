package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.ProductResponse;
import com.example.practicemodsenjava.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toProductResponseList(List<Product> products);  // Новый метод
}
