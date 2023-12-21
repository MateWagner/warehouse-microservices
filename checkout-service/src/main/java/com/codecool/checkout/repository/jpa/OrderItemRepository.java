package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
