package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(
            @Valid @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOwnOrders (
            @Valid @AuthenticationPrincipal UserJwt userJwt) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrdersByUserJwt(userJwt));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponse>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @AuthenticationPrincipal UserJwt userJwt) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.createOrder(userJwt));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderRequest));
    }

//    @PostMapping("/{orderId}/add")
//    public ResponseEntity<OrderResponse> addOrderItemToOrder(
//            @PathVariable UUID orderId,
//            @RequestBody @Valid OrderItemRequest orderItemRequest) {
//        return ResponseEntity.ok(orderService.addOrderItemToOrder(orderItemRequest, orderId));
//    }
//
//    @DeleteMapping("/{orderId}/delete/{order_item_id}")
//    public ResponseEntity<Void> removeOrderItemFromOrder(
//         @PathVariable UUID orderId,
//         @PathVariable(value = "order_item_id") UUID orderItemId) {
//        orderService.removeOrderItemFromOrder(orderId, orderItemId);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody OrderRequest orderRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.updateOrder(id, orderRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrder(@Valid @PathVariable UUID id) {

        orderService.deleteOrder(id);
    }

}
