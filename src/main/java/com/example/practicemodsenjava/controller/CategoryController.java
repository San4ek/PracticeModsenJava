package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.model.dto.request.CategoryRequest;
import com.example.practicemodsenjava.model.dto.response.CategoryResponse;
import com.example.practicemodsenjava.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable UUID id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping()
    public List<CategoryResponse> getCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping()
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest.name());
    }

    @PutMapping()
    public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.updateCategory(categoryRequest.id(), categoryRequest.name());
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
    }

}
