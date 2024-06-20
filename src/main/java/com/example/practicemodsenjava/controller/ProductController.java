package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.model.dto.request.ProductRequest;
import com.example.practicemodsenjava.model.dto.response.ProductResponse;
import com.example.practicemodsenjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable UUID id){
        return productService.getProductById(id);
    }

    @GetMapping("/{categoryId}")
    public List<ProductResponse> getProductsByCategoryId(@PathVariable UUID categoryId){
        return productService.getProductsByCategoryId(categoryId);
    }

    @PostMapping()
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest.categoryId(), productRequest.name());
    }

    @PutMapping()
    public ProductResponse updateProduct(@RequestBody ProductRequest productRequest){
        return productService.updateProduct(productRequest.id(), productRequest.name());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
    }

}
