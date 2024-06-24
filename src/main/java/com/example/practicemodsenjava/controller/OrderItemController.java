package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.model.dto.request.OrderItemRequest;
import com.example.practicemodsenjava.model.dto.response.OrderItemListResponse;
import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.service.impl.OrderItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/orders/{id}/items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemServiceImpl orderItemService;

    @GetMapping()
    public ResponseEntity<OrderItemListResponse> getOrderItemsByOrderId(@PathVariable UUID id){
        return new ResponseEntity<>(new OrderItemListResponse(orderItemService.getOrderItemsByOrderId(id)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderItemResponse> createOrderItem(@PathVariable UUID id, @RequestBody OrderItemRequest orderItemRequest){
        return new ResponseEntity<>(orderItemService.createOrderItem(id, orderItemRequest.productId(), orderItemRequest.amount()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteOrderItem(@PathVariable UUID id){
        orderItemService.deleteOrderItem(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
