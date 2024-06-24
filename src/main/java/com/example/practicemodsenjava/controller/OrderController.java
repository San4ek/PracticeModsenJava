package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.mapper.OrderMapper;
import com.example.practicemodsenjava.model.dto.request.OrderRequest;
import com.example.practicemodsenjava.model.dto.response.OrderListResponse;
import com.example.practicemodsenjava.model.dto.response.OrderResponse;
import com.example.practicemodsenjava.service.impl.OrderServiceImpl;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id) throws NoSuchElementException {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<OrderListResponse> getAllOrders(){
        return new ResponseEntity<>(new OrderListResponse(orderService.getAllOrders()), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<OrderListResponse> getOrdersByUserId(@PathVariable UUID userId){
        return new ResponseEntity<>(new OrderListResponse(orderService.getOrdersByUserId(userId)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderMapper.toOrder(orderRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable UUID id, @RequestBody OrderRequest orderRequest) throws NoSuchElementException{
        return new ResponseEntity<>(orderService.updateOrder(id, orderMapper.toOrder(orderRequest)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteOrder(@PathVariable UUID id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
