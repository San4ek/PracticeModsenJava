package by.modsen.practice.group11.repository;

import by.modsen.practice.group11.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategory_Id(UUID categoryId);
    List<Product> findByCategory_Name(String name);
}
