package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.entity.Product;
import by.modsen.practice.group11.model.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getCategory().getId(),
                product.getName()
        );
    }
}
