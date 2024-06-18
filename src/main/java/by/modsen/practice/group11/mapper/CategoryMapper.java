package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.entity.Category;
import by.modsen.practice.group11.model.dto.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
