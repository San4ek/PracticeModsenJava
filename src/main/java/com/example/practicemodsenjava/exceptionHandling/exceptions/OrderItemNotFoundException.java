package com.example.practicemodsenjava.exceptionHandling.exceptions;

import java.util.UUID;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(UUID itemId) {
        super("Order item not found with id: " + itemId);
    }
}