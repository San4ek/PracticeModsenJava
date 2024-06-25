package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.dto.request.ProductRequest;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.service.ProductService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Product Controller")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public ResponseEntity<ProductResponse> getProductById(
            @Valid @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }

    @GetMapping("categoryName/{category}")
    @Operation(summary = "Get product by category Name")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryName(
            @Valid @PathVariable String category) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductByCategoryName(category));
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create product")
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest productRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete product")
    public void deleteCategory(
            @Valid @PathVariable UUID id) {

        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateCategory(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody ProductRequest productRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.updateProduct(id, productRequest));
    }
}
