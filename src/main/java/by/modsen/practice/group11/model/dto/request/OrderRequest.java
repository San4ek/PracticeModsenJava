package by.modsen.practice.group11.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderRequest(
        @NotNull(message = "Personal info is mandatory")
        UUID personalInfo
) {}
