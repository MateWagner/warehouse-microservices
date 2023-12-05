package com.codecool.catalog_inventory.repository;

import com.codecool.catalog_inventory.modell.InventoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<InventoryProduct, Long> {
    Optional<InventoryProduct> findByItemPID(UUID itemPID);
}
