package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.ProductResponse;
import com.example.practicemodsenjava.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getCategory().getId(),
                product.getName()
        );
    }
}
