package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.mapper.OrderItemMapper;
import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.entity.Order;
import by.modsen.practice.group11.model.entity.OrderItem;
import by.modsen.practice.group11.model.entity.PersonalInfo;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.repository.OrderItemRepository;
import by.modsen.practice.group11.repository.UserRepository;
import by.modsen.practice.group11.service.OrderService;
import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.mapper.OrderMapper;
import by.modsen.practice.group11.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

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
    public OrderResponse createOrder(UserJwt userJwt) {

        Optional<User> optUser = userRepository.findById(userJwt.getId());

        PersonalInfo personalInfo = optUser.get().getPersonalInfo();
        Order savedOrder = new Order();
        savedOrder.setPersonalInfo(personalInfo);

        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponse addOrderItemToOrder(
            OrderItemRequest orderItemRequest, UUID orderId) {

        OrderItem orderItem = orderItemMapper.toOrderItem(orderItemRequest);
        Order order = orderRepository.findById(orderId).get();

        orderItem.setOrder(order);
        orderItemRepository.save(orderItem);

        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional
    public void removeOrderItemFromOrder(UUID orderId, UUID orderItemId) {
        orderItemRepository.deleteByOrderIdAndId(orderId, orderItemId);
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
