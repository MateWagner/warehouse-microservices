package com.codecool.warehouse.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long itemId;
    Long quantity;

    public boolean isProductAvailable(){
        return quantity > 0;
    }
    public Product(Long itemId, Long quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
