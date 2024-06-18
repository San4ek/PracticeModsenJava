package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.entity.Order;
import by.modsen.practice.group11.service.OrderService;
import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.mapper.OrderMapper;
import by.modsen.practice.group11.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import com.example.practicemodsenjava.exceptionHandling.GlobalExceptionHandler;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(UUID orderId) {
        Order order = getOrderOrThrow(orderId);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByPersonalInfoId(UUID personalInfoId) {
        List<Order> orders = orderRepository.findByPersonalInfoId(personalInfoId);
        return orders.stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponse createOrder(UUID userId, List<OrderItemRequest> orderItems) {
        Order order = new Order();
        // Set properties for order
        // order.setUser(...);
        // order.setOrderItems(...);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {
        Order order = getOrderOrThrow(orderId);
        orderRepository.delete(order);
    }

    private Order getOrderOrThrow(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(); // ToDo: Create exception
//                .orElseThrow(() -> new GlobalExceptionHandler("Order with id " + orderId + " not found"));
    }
}
