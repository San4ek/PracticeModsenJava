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
@Tag(name = "Product Controller")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Get product by id")
    public ResponseEntity<ProductResponse> getProductById(
            @Valid @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }


    @GetMapping("/get/category-name/{category}")
    @Operation(summary = "Get product by category Name")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryName(
            @Valid @PathVariable String category) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductByCategoryName(category));
    }

    @GetMapping("/get/all")
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductResponse>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create product")
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest productRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productRequest));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody ProductRequest productRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.updateProduct(id, productRequest));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(
            @Valid @PathVariable UUID id) {

        productService.deleteProduct(id);
    }
}
