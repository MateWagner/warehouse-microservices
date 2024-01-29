package com.codecool.checkout.service;

import com.codecool.checkout.dto.CachedProduct;
import com.codecool.checkout.modell.redis.OrderItemCache;
import com.codecool.checkout.repository.redis.OrderItemCacheRepository;
import com.codecool.checkout.utils.OrderItemCacheMapper;
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

    public void addItemsToCache(Map<UUID, Long> itemMap) {
        itemMap.keySet().forEach(itemPID -> {
            try {
                OrderItemCache cacheItem = getById(itemPID);
                cacheItem.addAmount(itemMap.get(itemPID));
                itemCacheRepository.save(cacheItem);
            } catch (HttpClientErrorException e) {
                OrderItemCache cacheItem = new OrderItemCache(itemPID, itemMap.get(itemPID));
                itemCacheRepository.save(cacheItem);
            }
        });
    }

    public CachedProduct getReservedOrderItems(UUID itemPID) {
        return OrderItemCacheMapper.toCachedProduct(getById(itemPID));
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

    public void removeItemsFromCache(Map<UUID, Long> itemMap) {
        itemMap.forEach(this::searchAndDecreaseCache);

    }

    private void searchAndDecreaseCache(UUID itemPID, Long amount) {
        OrderItemCache item = getById(itemPID);
        item.subtractAmount(amount);
        if (item.getAmount().equals(0L)) {
            itemCacheRepository.delete(item);
            return;
        }

        itemCacheRepository.save(item);
    }
}
