package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.ProductRequest;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import by.modsen.practice.group11.model.entity.Product;
import by.modsen.practice.group11.repository.CategoryRepository;
import by.modsen.practice.group11.repository.ProductRepository;
import by.modsen.practice.group11.service.ProductService;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import by.modsen.practice.group11.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(UUID productId) {

        return productMapper.toProductResponse(getProductOrThrow(productId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategoryId(UUID categoryId) {

        return productMapper.toProductResponseList(getProductsByCategory(categoryId));
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productMapper.toProductResponseList(productRepository.findAll());
    }

    @Override
    public List<ProductResponse> getProductByCategoryName(String categoryName) {

        return productMapper.toProductResponseList(
                getProductsByCategory(categoryName));
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {

        return productMapper.toProductResponse(
                productRepository.save(productMapper.toProduct(productRequest)));
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UUID productId, ProductRequest productRequest) {

        return productMapper.toProductResponse(
                productRepository.save(productMapper.partialUpdate(productRequest, getProductOrThrow(productId))));
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {

        getProductOrThrow(productId);
        productRepository.deleteById(productId);
    }


    private Product getProductOrThrow(UUID productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 61, "Can't find product by id = " + productId));
    }

    private List<Product> getProductsByCategory(String categoryName) {

        categoryRepository.findCategoryByName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 62, "Can't find category by name = " + categoryName));

        return productRepository.findByCategoryName(categoryName);
    }

    private List<Product> getProductsByCategory(UUID categoryId) {

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 63, "Can't find category by id = " + categoryId));

        return productRepository.findByCategoryId(categoryId);
    }
}
