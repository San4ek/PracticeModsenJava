package com.example.practicemodsenjava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class CategoryDTO {
    private UUID id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String name;
}




