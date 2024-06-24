package com.example.practicemodsenjava.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CategoryRequest(

        UUID id,

        @NotBlank(message = "Name is mandatory")
        @NotNull
        @Size(min = 1, max = 100, message = "Name should be between 1 and 100 characters")
        String name) {}

