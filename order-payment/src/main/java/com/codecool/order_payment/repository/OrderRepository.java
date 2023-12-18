package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByPublicID(UUID orderPID);
}
