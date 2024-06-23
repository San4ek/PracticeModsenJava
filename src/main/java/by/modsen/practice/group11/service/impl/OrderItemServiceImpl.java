package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.entity.OrderItem;
import by.modsen.practice.group11.service.OrderItemService;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.mapper.OrderItemMapper;
import by.modsen.practice.group11.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> getOrderItemsByOrderId(UUID orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);
        return orderItems.stream()
                .map(orderItemMapper::toOrderItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderItemResponse createOrderItem(UUID orderId, UUID productId, int amount) {
        OrderItem orderItem = new OrderItem();
        // Set properties for orderItem
        // orderItem.setOrder(...);
        // orderItem.setProduct(...);
        // orderItem.setAmount(amount);

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemResponse(savedOrderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(UUID orderItemId) {
        OrderItem orderItem = getOrderItemOrThrow(orderItemId);
        orderItemRepository.delete(orderItem);
    }

    private OrderItem getOrderItemOrThrow(UUID orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(); // ToDo: Create exception
//                .orElseThrow(() -> new GlobalExceptionHandler("OrderItem with id " + orderItemId + " not found"));
    }
}
