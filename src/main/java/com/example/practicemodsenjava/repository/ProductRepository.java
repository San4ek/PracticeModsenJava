package com.example.practicemodsenjava.repository;

import com.example.practicemodsenjava.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.category.id = :category")
    List<Product> findProductsByCategory(@Param("category") UUID category);

}
