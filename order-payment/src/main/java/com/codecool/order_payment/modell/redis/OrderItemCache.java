package com.codecool.order_payment.modell.redis;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("OrderItemCache")
@AllArgsConstructor
@Getter
@ToString
public class OrderItemCache implements Serializable {
    @Id
    private UUID itemPID;
    private Long amount;

    public void addToAmount(Long amount) {
        this.amount += amount;
    }
}
