package com.codecool.checkout.repository.redis;

import com.codecool.checkout.modell.redis.OrderItemCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemCacheRepository extends CrudRepository<OrderItemCache, UUID> {
}
