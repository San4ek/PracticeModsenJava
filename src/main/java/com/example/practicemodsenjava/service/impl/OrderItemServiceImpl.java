package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.model.dto.response.OrderItemResponse;
import com.example.practicemodsenjava.mapper.OrderItemMapper;
import com.example.practicemodsenjava.model.entity.OrderItem;
import com.example.practicemodsenjava.repository.OrderItemRepository;
import com.example.practicemodsenjava.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> getOrderItemsByOrderId(UUID orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);
        return orderItemMapper.toOrderItemResponseList(orderItems);
    }

    @Override
    @Transactional
    public OrderItemResponse createOrderItem(UUID orderId, UUID productId, int amount) {
        OrderItem orderItem = new OrderItem();
        // Устанавливаем свойства для orderItem
        // orderItem.setOrder(...);
        // orderItem.setProduct(...);
        //orderItem.setAmount(amount);

        orderItemRepository.saveAndFlush(orderItem);
        return orderItemMapper.toOrderItemResponse(orderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(UUID orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    private OrderItem getOrderItemOrThrow(UUID orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow();
    }
}
