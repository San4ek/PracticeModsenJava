package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.model.dto.request.OrderRequest;
import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable UUID id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/{userId}")
    public List<OrderResponse> getOrdersByUserId(@PathVariable UUID userId){
        return orderService.getOrdersByUserId(userId);
    }

    @PostMapping()
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest.userId(), orderRequest.orderItems());
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable UUID id){
        orderService.deleteOrder(id);
    }

}
