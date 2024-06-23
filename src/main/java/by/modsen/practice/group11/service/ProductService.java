package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.ProductRequest;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse getProductById(UUID productId);

    List<ProductResponse> getProductsByCategoryId(UUID categoryId);

    List<ProductResponse> getProductByCategoryName(String categoryName);

    ProductResponse createProduct(UUID categoryId, ProductRequest productRequest);

    ProductResponse updateProduct(UUID productId, ProductRequest productRequest);

    void deleteProduct(UUID productId);
}
