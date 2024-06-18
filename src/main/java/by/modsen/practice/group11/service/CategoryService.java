package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponse getCategoryById(UUID categoryId);

    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory(String categoryName);

    CategoryResponse updateCategory(UUID categoryId, String categoryName);

    void deleteCategory(UUID categoryId);
}
