package com.codecool.checkout.utils;

import com.codecool.checkout.modell.redis.OrderItemCache;
import com.codecool.checkout.dto.CachedProduct;

public class OrderItemCacheMapper {

    public static CachedProduct toCachedProduct(OrderItemCache itemCache) {
        return new CachedProduct(itemCache.getItemPID(), itemCache.getAmount());
    }
}
