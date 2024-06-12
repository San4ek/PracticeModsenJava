package com.example.practicemodsenjava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class ProductDTO {

    private UUID id;

    @NotNull(message = "Category ID is mandatory")
    private UUID categoryId;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String name;
}