package com.codecool.order_payment.utils;

import com.codecool.order_payment.dto.CachedProduct;
import com.codecool.order_payment.modell.redis.OrderItemCache;

public class OrderItemCacheMapper {

    public static CachedProduct toCachedProduct(OrderItemCache itemCache) {
        return new CachedProduct(itemCache.getItemPID(), itemCache.getAmount());
    }
}
