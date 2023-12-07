package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByPublicID(UUID addressPID);
}
