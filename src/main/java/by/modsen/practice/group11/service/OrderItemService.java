package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.response.OrderItemResponse;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {

    List<OrderItemResponse> getOrderItemsByOrderId(UUID orderId);

    OrderItemResponse createOrderItem(UUID orderId, UUID productId, int amount);

    void deleteOrderItem(UUID orderItemId);
}
