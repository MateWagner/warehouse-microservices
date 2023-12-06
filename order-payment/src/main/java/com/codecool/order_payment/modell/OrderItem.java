package com.codecool.order_payment.modell;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

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
    Long id;

    UUID itemPID;
    Integer amount;
}
