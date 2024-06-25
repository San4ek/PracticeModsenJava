package by.modsen.practice.group11.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//@Schema(description = "Category request")
public record CategoryRequest(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 1, max = 100, message = "Name should be between 1 and 100 characters")
//        @Schema(description = "Category name", example = "milk")
        String name
) {
}

