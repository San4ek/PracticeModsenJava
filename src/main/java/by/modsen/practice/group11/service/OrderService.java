package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse getOrderById(UUID orderId);

    List<OrderResponse> getAllOrders();

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse updateOrder(UUID orderId, OrderRequest orderRequest);

    void deleteOrder(UUID orderId);
}
