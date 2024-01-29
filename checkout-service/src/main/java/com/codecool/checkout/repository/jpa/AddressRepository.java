package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.Address;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByPublicID(UUID addressPID);

    Set<Address> findAllByUserID(UUID userID);

    @Query("SELECT a.publicID FROM Address a WHERE a.userID = :userID")
    UUID preferredAddressByUser(@Param("userID") UUID userID);
}
