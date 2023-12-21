package com.codecool.warehouse.repository;

import com.codecool.warehouse.modell.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<CatalogItem, Long> {
    Optional<CatalogItem> getCatalogItemByPublicIdAndIsActive(UUID itemPID, boolean isActive);

    Page<CatalogItem> getAllByIsActive(boolean isActive, PageRequest pageRequest);
}
