package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "OrderItem Controller")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Get orderItem by id")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    public ResponseEntity<OrderItemResponse> getById(
            @Valid @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getOrderItemByOrderItemId(id));
    }

    @GetMapping("/get/all/order-id/{orderId}")
    @Operation(summary = "Get all orderItem by orderId")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    public ResponseEntity<List<OrderItemResponse>> getAllByOrderId(
            @Valid @PathVariable UUID orderId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getOrderItemsByOrderId(orderId));
    }

    @GetMapping("/get/all/product-id/{productId}")
    @Operation(summary = "Get all orderItem by productId")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderItemResponse>> getAllByProductId(
            @Valid @PathVariable UUID productId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getOrdersItemByProductId(productId));
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all")
    public ResponseEntity<List<OrderItemResponse>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getAllOrdersItems());
    }

    @PostMapping("/create")
    @Operation(summary = "Create orderItem")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    public ResponseEntity<OrderItemResponse> createOrderItem(
            @Valid @RequestBody OrderItemRequest orderItemRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderItemService.createOrderItem(orderItemRequest));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update orderItem")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    public ResponseEntity<OrderItemResponse> updateOrderItem(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody OrderItemRequest orderItemRequest
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderItemService.updateOrderItem(id, orderItemRequest));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete orderItem by orderItemId")
    public void deleteOrderItemById(@Valid @PathVariable UUID id) {

        orderItemService.deleteOrderItem(id);
    }

    @DeleteMapping("delete/all/order-id/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete orderItem by orderId")
    public void deleteOrder(@PathVariable UUID orderId) {

        orderItemService.deleteOrderItemsByOrderId(orderId);
    }

}
