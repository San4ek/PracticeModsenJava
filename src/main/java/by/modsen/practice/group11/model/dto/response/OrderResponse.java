package by.modsen.practice.group11.model.dto.response;

import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID personalInfoId,
        List<OrderItemResponse> orderItems
) {}