package com.example.practicemodsenjava.model.dto.request;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(
        @NotNull(message = "Category ID is mandatory")
        UUID categoryId,

        @NotBlank(message = "Name is mandatory")
        @NotNull
        @Size(min = 1, max = 100, message = "Name should be between 1 and 100 characters")
        String name
) {}
