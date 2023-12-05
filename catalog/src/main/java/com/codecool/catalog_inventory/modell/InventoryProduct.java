package com.codecool.catalog_inventory.modell;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class InventoryProduct {
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "inventory_product_id_sequence",
            sequenceName = "inventory_product_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_product_id_sequence")
    Long id;

    @Column(
            unique = true,
            nullable = false
    )
    UUID itemPID;

    Long quantity;

    public InventoryProduct(UUID itemPID, Long quantity) {
        this.itemPID = itemPID;
        this.quantity = quantity;
    }
}
