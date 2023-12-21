package com.codecool.order_payment.modell.jpa;

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
public class Address {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "address_id_sequence",
            sequenceName = "address_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_sequence")
    private Long id;

    @Column(
            nullable = false
    )
    private UUID publicID;

    @Column(
            nullable = false
    )
    private UUID userID;

    @ManyToOne(
            optional = false
    )
    private Postcode postcode;

    @ManyToOne(
            optional = false
    )
    private City city;

    @ManyToOne(
            optional = false
    )
    private Street street;

    @ManyToOne(
            optional = false
    )
    private AreaName areaName;

    @ManyToOne(
            optional = false
    )
    private HouseNumber houseNumber;
    // TODO put it to its own table
    private Boolean isPreferred;
}
