package by.modsen.practice.group11.repository;

import by.modsen.practice.group11.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findAllByOrder_Id(UUID orderId);

    List<OrderItem> findAllByProduct_Id(UUID productId);

    void deleteById(UUID orderItemId);

    void deleteByOrder_Id(UUID orderId);
}
