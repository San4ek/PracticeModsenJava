package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.CategoryResponse;
import com.example.practicemodsenjava.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
