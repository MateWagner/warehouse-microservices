package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Long> {
}
