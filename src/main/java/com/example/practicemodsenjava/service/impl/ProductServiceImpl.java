package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.model.dto.response.ProductResponse;
import com.example.practicemodsenjava.mapper.ProductMapper;
import com.example.practicemodsenjava.model.entity.Product;
import com.example.practicemodsenjava.repository.ProductRepository;
import com.example.practicemodsenjava.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(UUID productId) {
        Product product = getProductOrThrow(productId);
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductResponseList(products);
    }

    @Override
    @Transactional
    public ProductResponse createProduct(Product product) {
        productRepository.saveAndFlush(product);  // Используем saveAndFlush вместо save
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UUID productId, Product productDetails) {
        Product product = getProductOrThrow(productId);
        // Обновляем поля product с productDetails
        productRepository.saveAndFlush(product);  // Используем saveAndFlush вместо save
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
    productRepository.deleteById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategoryId(UUID categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        return productMapper.toProductResponseList(products);
    }

    private Product getProductOrThrow(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow();
    }
}
