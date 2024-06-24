package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.entity.Category;
import by.modsen.practice.group11.repository.CategoryRepository;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomMapper {
    private final CategoryRepository categoryRepository;

    @Named(value = "categoryRefFromCategoryId")
    public Category takeStoryRefFromStoryRequestTo(UUID categoryId) {
        return  categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 56, "Can't find category by id " + categoryId));
    }
}
