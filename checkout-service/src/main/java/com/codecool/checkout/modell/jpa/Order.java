package com.codecool.checkout.modell.jpa;

import com.codecool.checkout.data.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
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
    private Long id;

    @Column(
            unique = true
    )
    private UUID publicID;

    private BigDecimal total;

    private UUID userID;

    private OrderStatus orderStatus;

    private LocalDateTime created;

    private LocalDateTime updated;
    private String email;
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "order_id"
    )
    private Set<OrderItem> orderItems = new HashSet<>();
}
