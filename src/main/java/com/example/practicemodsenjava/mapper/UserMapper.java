package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.request.UserRequest;
import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);  // Новый метод

    User toUser(UserRequest userRequest);
}