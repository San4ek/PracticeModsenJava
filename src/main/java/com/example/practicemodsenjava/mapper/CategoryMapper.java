package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.CategoryResponse;
import com.example.practicemodsenjava.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "categoryId")
    CategoryResponse toCategoryResponse(Category category);
}