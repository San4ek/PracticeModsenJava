package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.mapper.ProductMapper;
import com.example.practicemodsenjava.model.dto.request.ProductRequest;
import com.example.practicemodsenjava.model.dto.response.ProductListResponse;
import com.example.practicemodsenjava.model.dto.response.ProductResponse;
import com.example.practicemodsenjava.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<ProductListResponse> getAllProducts() {
        return new ResponseEntity<>(new ProductListResponse(productService.getAllProducts()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) throws NoSuchElementException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ProductListResponse> getProductsByCategoryId(@PathVariable UUID categoryId){
        return new ResponseEntity<>(new ProductListResponse(productService.getProductsByCategoryId(categoryId)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.createProduct(productMapper.toProduct(productRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id, @RequestBody ProductRequest productRequest) throws NoSuchElementException{
        return new ResponseEntity<>(productService.updateProduct(id, productMapper.toProduct(productRequest)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteProduct(@PathVariable UUID id) throws NoSuchElementException{
        productService.deleteProduct(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
