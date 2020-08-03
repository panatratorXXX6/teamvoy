package com.example.teamvoy.repository;

import com.example.teamvoy.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.order.id = ?1")
    void deleteAllByOrderId(Long orderId);

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.item.id = ?1")
    void deleteAllByItemId(Long itemId);
}
