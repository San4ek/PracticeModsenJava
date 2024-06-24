package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.CategoryRequest;
import by.modsen.practice.group11.model.dto.response.CategoryResponse;
import by.modsen.practice.group11.model.entity.Category;
import by.modsen.practice.group11.repository.CategoryRepository;
import by.modsen.practice.group11.service.CategoryService;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import by.modsen.practice.group11.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        return categoryMapper.toCategoryResponse(getCategoryOrThrow(categoryId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {

        return categoryMapper.toCategoryResponseList(categoryRepository.findAll());
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        return categoryMapper.toCategoryResponse(categoryRepository.save(categoryMapper.toCategory(categoryRequest)));
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(UUID categoryId, CategoryRequest categoryRequest) {

        return categoryMapper.toCategoryResponse(categoryRepository.save(categoryMapper.partialUpdate(categoryRequest, getCategoryOrThrow(categoryId))));
    }

    @Override
    @Transactional
    public void deleteCategory(UUID categoryId) {

        getCategoryOrThrow(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    private Category getCategoryOrThrow(UUID categoryId) {

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 21, "Can't find category by id = " + categoryId));
    }

}
