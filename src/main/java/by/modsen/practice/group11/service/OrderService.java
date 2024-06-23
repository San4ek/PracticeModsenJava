package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse getOrderById(UUID orderId);

    List<OrderResponse> getOrdersByPersonalInfoId(UUID personalInfoId);

    OrderResponse createOrder(UserJwt userJwt);

    OrderResponse addOrderItemToOrder(OrderItemRequest orderItemRequest, UUID orderId);

    void removeOrderItemFromOrder(UUID orderId, UUID orderItemId);

    void deleteOrder(UUID orderId);
}
