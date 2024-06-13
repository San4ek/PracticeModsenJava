package com.example.practicemodsenjava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class CategoryDTO {
    private UUID id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 100, message = "Name should be between 1 and 100 characters")
    private String name;
}




