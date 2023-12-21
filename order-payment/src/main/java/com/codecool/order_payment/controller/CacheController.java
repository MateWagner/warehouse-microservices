package com.codecool.order_payment.controller;

import com.codecool.order_payment.dto.CachedProduct;
import com.codecool.order_payment.service.OrderItemCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cache")
@RequiredArgsConstructor
public class CacheController {
    private final OrderItemCacheService orderItemCacheService;

    @GetMapping("{itemPID}")
    public CachedProduct getReservedOrderItems(@PathVariable UUID itemPID) {
        return orderItemCacheService.getReservedOrderItems(itemPID);
    }
}
