package com.example.practicemodsenjava.repository;

import com.example.practicemodsenjava.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
