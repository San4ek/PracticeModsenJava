package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse getOrderById(UUID orderId);

    List<OrderResponse> getOrdersByPersonalInfoId(UUID personalInfoId);

    OrderResponse createOrder(UUID userId, List<OrderItemRequest> orderItems);

    void deleteOrder(UUID orderId);
}
