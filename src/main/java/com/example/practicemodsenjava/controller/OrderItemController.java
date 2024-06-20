package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.model.dto.request.OrderItemRequest;
import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders/{id}/items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping()
    public List<OrderItemResponse> getOrderItemsByOrderId(@PathVariable UUID id){
        return orderItemService.getOrderItemsByOrderId(id);
    }

    @PostMapping()
    public OrderItemResponse createOrderItem(@PathVariable UUID id, @RequestBody OrderItemRequest orderItemRequest){
        return orderItemService.createOrderItem(id, orderItemRequest.productId(), orderItemRequest.amount());
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable UUID id){
        orderItemService.deleteOrderItem(id);
    }


}
