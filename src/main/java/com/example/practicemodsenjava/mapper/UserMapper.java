package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.login", target = "login")
    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.birthday", target = "birthday")
    @Mapping(source = "user.role", target = "role")
    UserResponse toUserResponse(User user);
}