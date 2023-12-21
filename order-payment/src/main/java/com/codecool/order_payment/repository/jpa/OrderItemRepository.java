package com.codecool.order_payment.repository.jpa;

import com.codecool.order_payment.modell.jpa.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
