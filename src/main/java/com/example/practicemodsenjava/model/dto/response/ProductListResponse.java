package com.example.practicemodsenjava.model.dto.response;

import java.util.List;

public record ProductListResponse(
    List<ProductResponse> products
) {}
