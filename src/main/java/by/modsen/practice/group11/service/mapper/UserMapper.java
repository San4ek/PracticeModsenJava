package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.UserRequest;
import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

// ToDo: Change mapper

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "personalInfo", target = "personalInfoResponse")
    @Mapping(source = "personalInfo.fullName", target = "personalInfoResponse.fullName")
    @Mapping(source = "personalInfo.gender", target = "personalInfoResponse.gender")
    @Mapping(source = "personalInfo.birthday", target = "personalInfoResponse.birthday")
    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personalInfo.id", ignore = true)
    @Mapping(target = "personalInfo.user", ignore = true)
    @Mapping(target = "personalInfo.orders", ignore = true)
    User toUser(UserRequest userRequest);
}
