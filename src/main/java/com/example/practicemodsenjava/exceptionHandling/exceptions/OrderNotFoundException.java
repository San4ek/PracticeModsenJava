package com.example.practicemodsenjava.exceptionHandling.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(UUID orderId) {
        super("Order not found with id: " + orderId);
    }
}