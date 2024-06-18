package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getLogin(),
                user.getRole()
        );
    }
}
