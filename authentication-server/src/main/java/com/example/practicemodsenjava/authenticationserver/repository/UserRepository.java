package com.example.practicemodsenjava.authenticationserver.repository;

import by.practicemodsenjava.authenticationserver.model.entity.OrderId;
import by.practicemodsenjava.authenticationserver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByLogin(String login);
}
