package com.example.practicemodsenjava.exceptionHandling.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(UUID productId) {
        super("Product not found with id: " + productId);
    }
}
