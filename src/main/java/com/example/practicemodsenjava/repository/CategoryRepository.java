package com.example.practicemodsenjava.repository;

import com.example.practicemodsenjava.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
