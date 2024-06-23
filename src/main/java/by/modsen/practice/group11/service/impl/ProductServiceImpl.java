package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.ProductRequest;
import by.modsen.practice.group11.model.entity.Category;
import by.modsen.practice.group11.model.entity.Product;
import by.modsen.practice.group11.repository.CategoryRepository;
import by.modsen.practice.group11.service.ProductService;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.service.mapper.ProductMapper;
import by.modsen.practice.group11.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// ToDo: Change service methods

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;


    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(UUID productId) {
        Product product = getProductOrThrow(productId);
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategoryId(UUID categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductByCategoryName(String categoryName) {
        List<Product> products = productRepository.findByCategory_Name(categoryName);
        return products.stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @Override
    @Transactional
    public ProductResponse createProduct(UUID categoryId, ProductRequest productRequest) {
        // TODO добавить исключения
        Category category = categoryRepository.findById(categoryId).get();

        Product savedProduct = productRepository.save(productMapper.toProduct(productRequest));
        savedProduct.setCategory(category);
        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UUID productId, ProductRequest productRequest) {
        Product product = getProductOrThrow(productId);
        product.setName(productRequest.name());

        Product updatedProduct = productRepository.save(product);
        return productMapper.toProductResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
        Product product = getProductOrThrow(productId);
        productRepository.delete(product);
    }


    private Product getProductOrThrow(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(); // ToDo: Create exception
//                .orElseThrow(() -> new GlobalExceptionHandler("Product with id " + productId + " not found"));
    }
}
