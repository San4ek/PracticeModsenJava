package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.CategoryRequest;
import by.modsen.practice.group11.model.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponse getCategoryById(UUID categoryId);

    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(UUID categoryId, CategoryRequest categoryRequest);

    void deleteCategory(UUID categoryId);
}
