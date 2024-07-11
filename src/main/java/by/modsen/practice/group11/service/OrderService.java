package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse getOrderById(UUID orderId);

    List<OrderResponse> getAllOrdersByUserJwt(UserJwt userJwt);

    List<OrderResponse> getAllOrders();

    OrderResponse createOrder(UserJwt userJwt);

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse updateOrder(UUID orderId, OrderRequest orderRequest);

    void deleteOrder(UserJwt userJwt, UUID orderId);
}
