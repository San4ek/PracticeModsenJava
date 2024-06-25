package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.dto.request.OrderItemRequest;
import by.modsen.practice.group11.model.dto.response.OrderItemResponse;
import by.modsen.practice.group11.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getById(
            @Valid @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getOrderItemByOrderItemId(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getAllByOrderId(
            @Valid @PathVariable UUID orderId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getOrderItemsByOrderId(orderId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderItemResponse>> getAllByProductId(
            @Valid @PathVariable UUID productId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getOrdersItemByProductId(productId));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemService.getAllOrdersItems());
    }

    @PostMapping
    public ResponseEntity<OrderItemResponse> createOrderItem(
            @Valid @RequestBody OrderItemRequest orderItemRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderItemService.createOrderItem(orderItemRequest));
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemResponse> updateOrderItem(
            @Valid @PathVariable UUID orderItemId,
            @Valid @RequestBody OrderItemRequest orderItemRequest
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderItemService.updateOrderItem(orderItemId, orderItemRequest));
    }

    @DeleteMapping("/{orderItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@Valid @PathVariable UUID orderItemId) {

        orderItemService.deleteOrderItem(orderItemId);
    }

    @DeleteMapping("/order/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable UUID orderId) {

        orderItemService.deleteOrderItemsByOrderId(orderId);
    }

}
