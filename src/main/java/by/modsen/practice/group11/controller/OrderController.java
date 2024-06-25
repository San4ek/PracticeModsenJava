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
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> getOrder(
            @Valid @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
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
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponse> createOwnOrder(
            @Valid @AuthenticationPrincipal UserJwt userJwt) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.createOrder(userJwt));
    }

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> updateOrder(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody OrderRequest orderRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.updateOrder(id, orderRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrder(@Valid @PathVariable UUID id) {

        orderService.deleteOrder(id);
    }

}
