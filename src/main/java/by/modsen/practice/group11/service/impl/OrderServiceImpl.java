package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import by.modsen.practice.group11.service.mapper.OrderItemMapper;
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
import by.modsen.practice.group11.service.mapper.OrderMapper;
import by.modsen.practice.group11.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(UUID orderId) {

        return orderMapper.toOrderResponse(getOrderOrThrow(orderId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {

        return orderMapper.toOrderResponseList(orderRepository.findAll());
    }

    //ToDO change with userJWt

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {

        return orderMapper.toOrderResponse(orderRepository.save(orderMapper.toOrder(orderRequest)));
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(UUID orderId, OrderRequest orderRequest) {

        return orderMapper.toOrderResponse(orderRepository.save(orderMapper.partialUpdate(orderRequest, getOrderOrThrow(orderId))));
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {

        getOrderOrThrow(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByPersonalInfoId(UUID personalInfoId) {
        List<Order> orders = orderRepository.findByPersonalInfoId(personalInfoId);
        return orders.stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    private Order getOrderOrThrow(UUID orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 41, "Can't find order by id = " + orderId));
    }


    //ToDO: It is useful idea,
//    @Override
//    @Transactional
//    public OrderResponse createOrder(UserJwt userJwt) {
//
//        Optional<User> optUser = userRepository.findById(userJwt.getId());
//
//        PersonalInfo personalInfo = optUser.get().getPersonalInfo();
//        Order savedOrder = new Order();
//        savedOrder.setPersonalInfo(personalInfo);
//
//        return orderMapper.toOrderResponse(savedOrder);
//    }
}
