package com.codecool.order_payment.modell;

import com.codecool.order_payment.data.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Order {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_sequence")
    Long id;

    @Column(
            unique = true
    )
    UUID publicID;
    BigDecimal total;
    UUID userID;
    OrderStatus orderStatus;

    Set<OrderItem> orderItems;
}
