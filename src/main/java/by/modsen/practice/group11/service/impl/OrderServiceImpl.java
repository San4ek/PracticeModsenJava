package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.model.entity.Order;
import by.modsen.practice.group11.model.enums.Role;
import by.modsen.practice.group11.repository.OrderRepository;
import by.modsen.practice.group11.service.OrderService;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import by.modsen.practice.group11.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    public List<OrderResponse> getAllOrdersByUserJwt(UserJwt userJwt) {

        return orderMapper.toOrderResponseList(orderRepository.findAllByUser_Id(userJwt.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {

        return orderMapper.toOrderResponseList(orderRepository.findAll());
    }

    @Override
    @Transactional
    public OrderResponse createOrder(UserJwt userJwt) {

        return orderMapper.toOrderResponse(orderRepository.save(orderMapper.toOrder(userJwt)));
    }

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
    public void deleteOrder(UserJwt userJwt, UUID orderId) throws DataIntegrityViolationException {

        Order order = getOrderOrThrow(orderId);
        List<String> accessRights = userJwt.getAccessRights().stream().map(GrantedAuthority::getAuthority).toList();

        if (!accessRights.contains(Role.ROLE_ADMIN.name())) {
            if (order.getUser().getId() != userJwt.getId()) {
                throw new DataIntegrityViolationException("Can't delete this order. Access denied.");
            }
        }

        orderRepository.deleteById(orderId);
    }

    private Order getOrderOrThrow(UUID orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 41, "Can't find order by id = " + orderId));
    }
}
