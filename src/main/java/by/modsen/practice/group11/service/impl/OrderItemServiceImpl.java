package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.model.entity.OrderItem;
import by.modsen.practice.group11.repository.OrderItemRepository;
import by.modsen.practice.group11.service.OrderItemService;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import by.modsen.practice.group11.service.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public OrderItemResponse getOrderItemByOrderItemId(UUID orderItemId) {

        return orderItemMapper.toOrderItemResponse(getOrderItemOrThrow(orderItemId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> getOrderItemsByOrderId(UUID orderId) {

        return orderItemMapper.toOrderItemResponseList(orderItemRepository.findAllByOrder_Id(orderId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> getOrdersItemByItemsId(UUID productId) {

        return orderItemMapper.toOrderItemResponseList(orderItemRepository.findAllByProduct_Id(productId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> getAllOrdersItems() {

        return orderItemMapper.toOrderItemResponseList(orderItemRepository.findAll());
    }

    @Override
    @Transactional
    public OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest) {

        return orderItemMapper.toOrderItemResponse(orderItemRepository.save(orderItemMapper.toOrderItem(orderItemRequest)));
    }

    @Override
    @Transactional
    public OrderItemResponse updateOrderItem(UUID orderItemId,  OrderItemRequest orderItemRequest) {

        return orderItemMapper.toOrderItemResponse(orderItemRepository.save(orderItemMapper.partialUpdate(orderItemRequest, getOrderItemOrThrow(orderItemId))));
    }

    @Override
    @Transactional
    public void deleteOrderItem(UUID orderItemId) {

        getOrderItemOrThrow(orderItemId);
        orderItemRepository.deleteById(orderItemId);
    }

    @Override
    @Transactional
    public void deleteOrderItemsByOrderId(UUID orderId) {


        orderItemRepository.deleteByOrder_Id(orderId);
    }

    private OrderItem getOrderItemOrThrow(UUID orderItemId) {

        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 31, "Can't find order item by id = " + orderItemId));
    }
}
