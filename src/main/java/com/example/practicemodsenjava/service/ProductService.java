package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(UUID id, ProductDTO productDTO);

    void deleteProduct(UUID id);

    ProductDTO getProductById(UUID id);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getProductsByCategoryId(UUID categoryId);
}