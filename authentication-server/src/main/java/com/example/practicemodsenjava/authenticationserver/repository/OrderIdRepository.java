package com.example.practicemodsenjava.authenticationserver.repository;

import by.practicemodsenjava.authenticationserver.model.entity.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderIdRepository extends JpaRepository<OrderId, UUID> {

}
