package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(UUID id, CategoryDTO categoryDTO);

    void deleteCategory(UUID id);

    CategoryDTO getCategoryById(UUID id);

    List<CategoryDTO> getAllCategories();
}