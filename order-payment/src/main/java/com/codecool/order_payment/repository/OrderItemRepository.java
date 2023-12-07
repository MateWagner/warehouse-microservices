package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
