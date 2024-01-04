package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByPublicID(UUID addressPID);

    Set<Address> findAllByUserID(UUID userID);
}
