package by.modsen.practice.group11.model.dto.request;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//@Schema(description = "Product request")
public record ProductRequest(
        @NotNull(message = "Category ID is mandatory")
//        @Schema(description = "Category id")
        UUID categoryId,

        @NotBlank(message = "Name is mandatory")
        @NotNull
        @Size(min = 1, max = 100, message = "Name should be between 1 and 100 characters")
//        @Schema(description = "Product name", example = "milk")
        String name
) {
}
