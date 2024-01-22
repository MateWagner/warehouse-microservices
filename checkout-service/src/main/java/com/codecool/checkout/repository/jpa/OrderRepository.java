package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.data.OrderStatus;
import com.codecool.checkout.modell.jpa.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByPublicID(UUID orderPID);

    @Query("SELECT o.total FROM Order o WHERE o.publicID = :pid")
    BigDecimal orderTotalByPublicID(@Param("pid") UUID pid);

    @Query("SELECT o.orderStatus FROM Order o WHERE o.publicID = :pid")
    OrderStatus orderStatus(@Param("pid") UUID pid);
}
