package com.codecool.checkout.service;

import com.codecool.checkout.dto.CachedProduct;
import com.codecool.checkout.modell.redis.OrderItemCache;
import com.codecool.checkout.repository.redis.OrderItemCacheRepository;
import com.codecool.checkout.utils.OrderItemCacheMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderItemCacheServiceTest {
    @Mock
    private OrderItemCacheRepository itemCacheRepository;

    @InjectMocks
    private OrderItemCacheService orderItemCacheService;
    private final UUID itemPID = UUID.randomUUID();

    @Test
    void addItemToCacheWillIncreaseAmountOnItem() {
        Map<UUID, Long> items = new HashMap<>();
        items.put(itemPID, 3L);
        OrderItemCache itemCache = OrderItemCache.builder()
                .itemPID(itemPID)
                .amount(1L)
                .build();
        long expected = 4L;

        when(itemCacheRepository.findById(itemPID)).thenReturn(Optional.of(itemCache));

        orderItemCacheService.addItemsToCache(items);

        assertEquals(expected, itemCache.getAmount());

        verify(itemCacheRepository, times(1)).findById(itemPID);
    }

    @Test
    void addItemToCacheWillAddNewItem() {
        long amount = 3L;
        Map<UUID, Long> items = new HashMap<>();
        items.put(itemPID, amount);
        OrderItemCache savedItem = OrderItemCache.builder()
                .itemPID(itemPID)
                .amount(amount)
                .build();

        when(itemCacheRepository.findById(itemPID)).thenReturn(Optional.empty());
        when(itemCacheRepository.save(any(OrderItemCache.class))).thenReturn(savedItem);

        orderItemCacheService.addItemsToCache(items);

        assertEquals(amount, savedItem.getAmount());

        verify(itemCacheRepository, times(1)).findById(itemPID);
        verify(itemCacheRepository, times(1)).save(savedItem);
    }

    @Test
    void getReservedAmountOnCachedItem() {
        OrderItemCache itemCache = OrderItemCache.builder()
                .itemPID(itemPID)
                .amount(1L)
                .build();
        CachedProduct expected = OrderItemCacheMapper.toCachedProduct(itemCache);

        when(itemCacheRepository.findById(itemPID)).thenReturn(Optional.of(itemCache));

        CachedProduct actual = orderItemCacheService.getReservedOrderItems(itemPID);

        assertEquals(expected, actual);

        verify(itemCacheRepository, times(1)).findById(itemPID);
    }

    @Test
    void getReservedAmountOnNonCachedItem() {
        when(itemCacheRepository.findById(itemPID)).thenReturn(Optional.empty());

        HttpClientErrorException actual = assertThrows(HttpClientErrorException.class, () -> orderItemCacheService.getReservedOrderItems(itemPID));
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());

        verify(itemCacheRepository, times(1)).findById(itemPID);
    }

    @Test
    void getByIdOnNotCachedItem() {
        when(itemCacheRepository.findById(itemPID)).thenReturn(Optional.empty());

        HttpClientErrorException actual = assertThrows(HttpClientErrorException.class, () -> orderItemCacheService.getById(itemPID));
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());

        verify(itemCacheRepository, times(1)).findById(itemPID);
    }

    @Test
    void getCachedById() {
        OrderItemCache expected = OrderItemCache.builder()
                .itemPID(itemPID)
                .amount(1L)
                .build();

        when(itemCacheRepository.findById(itemPID)).thenReturn(Optional.of(expected));

        OrderItemCache actual = orderItemCacheService.getById(itemPID);

        assertEquals(expected, actual);

        verify(itemCacheRepository, times(1)).findById(itemPID);
    }
}
