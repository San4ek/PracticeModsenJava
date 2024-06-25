package by.modsen.practice.group11.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//@Schema(description = "Order request")
public record OrderRequest(
        @NotNull(message = "Order id is mandatory")
//        @Schema(description = "User id")
        UUID userId
) {
}
