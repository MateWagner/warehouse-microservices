package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
