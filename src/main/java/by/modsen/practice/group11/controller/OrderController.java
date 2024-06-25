package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.OrderRequest;
import by.modsen.practice.group11.model.dto.response.OrderResponse;
import by.modsen.practice.group11.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Tag(name = "Order Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public ResponseEntity<OrderResponse> getOrder(
            @Valid @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @GetMapping
    @Operation(summary = "Get all own orders")
    public ResponseEntity<List<OrderResponse>> getAllOwnOrders (
            @Valid @AuthenticationPrincipal UserJwt userJwt) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrdersByUserJwt(userJwt));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all orders")
    public ResponseEntity<List<OrderResponse>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @PostMapping
    @Operation(summary = "Create order")
    public ResponseEntity<OrderResponse> createOrder(
            @AuthenticationPrincipal UserJwt userJwt) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.createOrder(userJwt));
    }

    @PostMapping
    @Operation(summary = "Create order by putting orderRequest")
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order")
    public ResponseEntity<OrderResponse> updateOrder(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody OrderRequest orderRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.updateOrder(id, orderRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete order")
    public void removeOrder(@Valid @PathVariable UUID id) {

        orderService.deleteOrder(id);
    }

}
