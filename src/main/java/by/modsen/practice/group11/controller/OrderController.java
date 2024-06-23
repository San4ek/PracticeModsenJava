package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@PreAuthorize("hasRole('CUSTOMER')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrderByPersonalId(
            @Valid @RequestBody UUID personalId) {
        return ResponseEntity.ok(orderService.getOrdersByPersonalInfoId(personalId));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @AuthenticationPrincipal UserJwt userJwt) {
        return ResponseEntity.ok(orderService.createOrder(userJwt));
    }

    @PostMapping("/{orderId}/add")
    public ResponseEntity<OrderResponse> addOrderItemToOrder(
            @PathVariable UUID orderId,
            @RequestBody @Valid OrderItemRequest orderItemRequest) {
        return ResponseEntity.ok(orderService.addOrderItemToOrder(orderItemRequest, orderId));
    }

    @DeleteMapping("/{orderId}/delete/{order_item_id}")
    public ResponseEntity<Void> removeOrderItemFromOrder(
         @PathVariable UUID orderId,
         @PathVariable(value = "order_item_id") UUID orderItemId) {
        orderService.removeOrderItemFromOrder(orderId, orderItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> removeOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
