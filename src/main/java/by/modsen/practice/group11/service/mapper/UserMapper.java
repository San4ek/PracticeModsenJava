package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.UserRequest;
import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CustomMapper.class)
public interface UserMapper {

    @Mapping(target = "personalInfoId", source = "personalInfo.id")
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    @Mapping(source = "personalInfoId", target = "personalInfo", qualifiedByName = "personalInfoRefFromPersonalInfoId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tokenRefresh", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toUser(UserRequest userRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "personalInfoId", target = "personalInfo", qualifiedByName = "personalInfoRefFromPersonalInfoId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tokenRefresh", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User partialUpdate(UserRequest userRequest, @MappingTarget User user);
}
