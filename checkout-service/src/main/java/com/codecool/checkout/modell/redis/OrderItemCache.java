package com.codecool.checkout.modell.redis;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("OrderItemCache")
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class OrderItemCache implements Serializable {
    @Id
    private UUID itemPID;
    private Long amount;

    public void addAmount(Long amount) {
        this.amount += amount;
    }
}
