package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.entity.Category;
import by.modsen.practice.group11.repository.CategoryRepository;
import by.modsen.practice.group11.model.entity.*;
import by.modsen.practice.group11.repository.*;
import by.modsen.practice.group11.repository.CategoryRepository;
import by.modsen.practice.group11.repository.PersonalInfoRepository;
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
    private final PersonalInfoRepository personalInfoRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Named("categoryRefFromCategoryId")
    public Category takeStoryRefFromStoryRequestTo(UUID categoryId) {

        return  categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 56, "Can't find category by id " + categoryId));
    }

    @Named("personalInfoRefFromPersonalInfoId")
    public PersonalInfo takePersonalInfoRefFromPersonalInfoRequestTo(UUID personalInfoId) {

        return  personalInfoRepository.findById(personalInfoId).
                orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 66, "Can't find personal info by id " + personalInfoId));
    }

    @Named("orderRefFromOrderId")
    public Order takeOrderRefFromOrderRequestTo(UUID orderId) {

        return  orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 76, "Can't find order by id " + orderId));
    }

    @Named("productRefFromProductId")
    public Product takeProductRefFromProductRequestTo(UUID productId) {

        return  productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 86, "Can't find product by id " + productId));
    }

    @Named("userRefFromUserId")
    public User takeUserRefFromUserRequestTo(UUID userId) {

        return  userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 96, "Can't find user by id " + userId));
    }
}
