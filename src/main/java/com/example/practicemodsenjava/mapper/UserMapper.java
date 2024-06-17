package com.example.practicemodsenjava.mapper;

import com.example.practicemodsenjava.model.dto.response.UserResponse;
import com.example.practicemodsenjava.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // TODO
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getLogin(),
                user.getRole()
        );
    }
}
