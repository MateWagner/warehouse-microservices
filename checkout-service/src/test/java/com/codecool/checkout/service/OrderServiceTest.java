package com.codecool.checkout.service;

import com.codecool.checkout.api.WarehouseApiClient;
import com.codecool.checkout.api.dto.PriceRequest;
import com.codecool.checkout.api.dto.PriceResponse;
import com.codecool.checkout.data.OrderStatus;
import com.codecool.checkout.dto.NewOrderDTO;
import com.codecool.checkout.dto.OrderDTO;
import com.codecool.checkout.modell.jpa.Order;
import com.codecool.checkout.repository.jpa.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private WarehouseApiClient warehouseApi;
    @Mock
    private OrderItemCacheService orderItemCacheService;
    @Mock
    private RabbitMQService rabbitMQService;

    @InjectMocks
    private OrderService orderService;

    private final UUID orderPID = UUID.randomUUID();


    @Test
    void placeOrder() {
        UUID userID = UUID.randomUUID();
        UUID itemPID = UUID.randomUUID();
        Map<UUID, Long> itemMap = new HashMap<>();
        itemMap.put(itemPID, 2L);
        NewOrderDTO newOrderDTO = new NewOrderDTO(userID, itemMap);

        Map<UUID, BigDecimal> priceResponseMap = new HashMap<>();
        priceResponseMap.put(itemPID, BigDecimal.valueOf(10.25));
        PriceResponse priceResponse = new PriceResponse(priceResponseMap);

        Order order = Order.builder()
                .publicID(orderPID)
                .build();
        when(warehouseApi.getPrices(any(PriceRequest.class))).thenReturn(priceResponse);
        doNothing().when(orderItemCacheService).addItemsToCache(anyMap());
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        doNothing().when(rabbitMQService).sendOrderChangeMail(any(Order.class));

        UUID result = orderService.placeOrder(newOrderDTO);

        assertEquals(orderPID, result);

        verify(warehouseApi, times(1)).getPrices(any(PriceRequest.class));
        verify(orderItemCacheService, times(1)).addItemsToCache(anyMap());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(rabbitMQService).sendOrderChangeMail(any(Order.class));
    }

    @Test
    void getOrderByPID() {
        Order order = Order.builder().publicID(orderPID).build();
        when(orderRepository.findByPublicID(orderPID)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderByPublicID(orderPID);

        assertEquals(order, result);

        verify(orderRepository, times(1)).findByPublicID(orderPID);
    }

    @Test
    void getNonExistOrderByPID() {
        when(orderRepository.findByPublicID(orderPID)).thenReturn(Optional.empty());

        final HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> orderService.getOrderByPublicID(orderPID));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(orderRepository, times(1)).findByPublicID(orderPID);
    }

    @Test
    void getOrderDTOByPID() {
        final LocalDateTime now = LocalDateTime.now();
        Order order = Order.builder()
                .publicID(orderPID)
                .userID(orderPID)
                .created(now)
                .updated(now)
                .total(BigDecimal.ZERO)
                .orderStatus(OrderStatus.RESERVED)
                .orderItems(Collections.emptySet())
                .build();
        when(orderRepository.findByPublicID(orderPID)).thenReturn(Optional.of(order));

        OrderDTO result = orderService.getOrderDTOByPID(orderPID);

        assertEquals(order.getPublicID(), result.orderPID());

        verify(orderRepository, times(1)).findByPublicID(orderPID);
    }

    @Test
    void getNonExistOrderDTOByPID() {
        when(orderRepository.findByPublicID(orderPID)).thenReturn(Optional.empty());

        final HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> orderService.getOrderDTOByPID(orderPID));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(orderRepository, times(1)).findByPublicID(orderPID);
    }
}
