package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.dto.request.CategoryRequest;
import by.modsen.practice.group11.model.dto.response.CategoryResponse;
import by.modsen.practice.group11.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Tag(name = "Category controller")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Get category by id")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Valid @PathVariable("id") UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getCategoryById(id));
    }

    @GetMapping("/get/all")
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryResponse>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create category")
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.createCategory(categoryRequest));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update category")
    public ResponseEntity<CategoryResponse> updateCategory(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody CategoryRequest categoryRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.updateCategory(id, categoryRequest));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete category")
    public void deleteCategoryById(@Valid @PathVariable("id") UUID id) {

        categoryService.deleteCategory(id);
    }
}
