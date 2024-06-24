package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.CategoryRequest;
import by.modsen.practice.group11.model.dto.response.CategoryResponse;
import by.modsen.practice.group11.model.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);

    Category toCategory(CategoryRequest categoryRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryRequest categoryRequest, @MappingTarget Category category);
}
