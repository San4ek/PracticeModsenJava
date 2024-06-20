package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.CategoryResponse;
import com.example.practicemodsenjava.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories); // Новый метод
}
