package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getLogin(),
                user.getFullName(),
                user.getGender(),
                user.getBirthday(),
                user.getRole()
        );
    }
}
