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

    private UUID publicID;

    private UUID userPID;

    @OneToOne
    private Postcode postcode;

    @OneToOne
    private City city;

    @OneToOne
    private Street street;

    @OneToOne
    private AreaName areaName;

    @OneToOne
    private HouseNumber houseNumber;

    private Boolean isPreferred;
}
