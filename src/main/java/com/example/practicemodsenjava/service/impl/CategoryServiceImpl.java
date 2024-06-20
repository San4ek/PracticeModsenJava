package com.example.practicemodsenjava.service.impl;

import com.example.practicemodsenjava.model.dto.response.CategoryResponse;
import com.example.practicemodsenjava.mapper.CategoryMapper;
import com.example.practicemodsenjava.model.entity.Category;
import com.example.practicemodsenjava.repository.CategoryRepository;
import com.example.practicemodsenjava.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(UUID categoryId) {
        Category category = getCategoryOrThrow(categoryId);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryResponseList(categories);
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.saveAndFlush(category);  // Используем saveAndFlush вместо save
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(UUID categoryId, String categoryName) {
        Category category = getCategoryOrThrow(categoryId);
        category.setName(categoryName);
        categoryRepository.saveAndFlush(category);  // Используем saveAndFlush вместо save
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    private Category getCategoryOrThrow(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow();
    }
}