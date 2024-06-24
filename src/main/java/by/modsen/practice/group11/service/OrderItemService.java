package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {

    OrderItemResponse getOrderItemByOrderItemId(UUID orderItemId);

    List<OrderItemResponse> getOrderItemsByOrderId(UUID orderId);

    List<OrderItemResponse> getOrdersItemByItemsId(UUID productId);

    List<OrderItemResponse> getAllOrdersItems();

    OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest);

    OrderItemResponse updateOrderItem(UUID orderItemId,  OrderItemRequest orderItemRequest);

    void deleteOrderItem(UUID orderItemId);

    void deleteOrderItemsByOrderId(UUID orderId);
}
