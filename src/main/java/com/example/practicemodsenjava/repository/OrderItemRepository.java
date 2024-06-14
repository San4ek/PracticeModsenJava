package com.example.practicemodsenjava.repository;

import com.example.practicemodsenjava.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
