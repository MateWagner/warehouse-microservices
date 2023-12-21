package com.codecool.order_payment.dev;

import com.codecool.order_payment.modell.redis.OrderItemCache;
import com.codecool.order_payment.repository.redis.OrderItemCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("DEV")
@RequiredArgsConstructor
public class CacheInitializer implements CommandLineRunner {
    private final OrderItemCacheRepository cacheRepository;

    @Override
    public void run(String... args) throws Exception {
        Iterable<OrderItemCache> cacheRepositoryAll = cacheRepository.findAll();
        cacheRepository.deleteAll(cacheRepositoryAll);
    }
}
