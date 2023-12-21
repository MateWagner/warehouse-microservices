package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.redis.OrderItemCache;
import com.codecool.order_payment.repository.redis.OrderItemCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemCacheService {
    private final OrderItemCacheRepository itemCacheRepository;

    public void addItemsToCache(Map<UUID, Long> items) {
        items.keySet().forEach(itemPID -> {
            try {
                OrderItemCache cacheItem = getById(itemPID);
                cacheItem.addToAmount(items.get(itemPID));
                itemCacheRepository.save(cacheItem);
            } catch (Exception e) {
                OrderItemCache cacheItem = new OrderItemCache(itemPID, items.get(itemPID));
                itemCacheRepository.save(cacheItem);
            }
        });
    }

    protected OrderItemCache getById(UUID itemPID) {
        return itemCacheRepository.findById(itemPID)
                .orElseThrow(() ->
                        new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find item in the cache with ID: " + itemPID
                        )
                );
    }
}
