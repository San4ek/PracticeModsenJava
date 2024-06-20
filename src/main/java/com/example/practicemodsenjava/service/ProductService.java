package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.response.ProductResponse;
import com.example.practicemodsenjava.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse getProductById(UUID productId);
    List<ProductResponse> getAllProducts();
    ProductResponse createProduct(Product product);
    ProductResponse updateProduct(UUID productId, Product productDetails);
    void deleteProduct(UUID productId);
    List<ProductResponse> getProductsByCategoryId(UUID categoryId); // Новый метод
}
