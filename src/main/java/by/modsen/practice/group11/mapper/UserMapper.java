package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toUserResponse(User user);

}
