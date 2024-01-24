package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByPublicID(UUID orderPID);
}
