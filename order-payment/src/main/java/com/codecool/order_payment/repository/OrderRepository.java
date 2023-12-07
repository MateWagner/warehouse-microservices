package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
