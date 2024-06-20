package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.entity.Category;
import by.modsen.practice.group11.service.CategoryService;
import by.modsen.practice.group11.model.dto.response.CategoryResponse;
import by.modsen.practice.group11.mapper.CategoryMapper;
import by.modsen.practice.group11.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

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
        return categories.stream()
                .map(categoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(savedCategory);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(UUID categoryId, String categoryName) {
        Category category = getCategoryOrThrow(categoryId);
        category.setName(categoryName);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID categoryId) {
        Category category = getCategoryOrThrow(categoryId);
        categoryRepository.delete(category);
    }

    private Category getCategoryOrThrow(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(); // ToDo: Create exception
//                .orElseThrow(() -> new GlobalExceptionHandler("Category with id " + categoryId + " not found"));
    }
}
