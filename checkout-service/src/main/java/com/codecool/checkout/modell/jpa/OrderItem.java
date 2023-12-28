package com.codecool.checkout.modell.jpa;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class OrderItem {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "order_item_id_sequence",
            sequenceName = "order_item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_sequence")
    private Long id;

    private UUID itemPID;

    private Long amount;
    private BigDecimal unitPrice;

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(amount));
    }
}
