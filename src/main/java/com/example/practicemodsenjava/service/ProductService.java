package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse getProductById(UUID productId);

    List<ProductResponse> getProductsByCategoryId(UUID categoryId);

    ProductResponse createProduct(UUID categoryId, String productName);

    ProductResponse updateProduct(UUID productId, String productName);

    void deleteProduct(UUID productId);
}
