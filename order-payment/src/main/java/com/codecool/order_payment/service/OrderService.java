package com.codecool.order_payment.service;

import com.codecool.order_payment.api.WarehouseApiClient;
import com.codecool.order_payment.api.dto.PriceRequest;
import com.codecool.order_payment.api.dto.PriceResponse;
import com.codecool.order_payment.data.OrderStatus;
import com.codecool.order_payment.dto.NewOrderDTO;
import com.codecool.order_payment.dto.OrderDTO;
import com.codecool.order_payment.modell.jpa.Order;
import com.codecool.order_payment.modell.jpa.OrderItem;
import com.codecool.order_payment.repository.jpa.OrderRepository;
import com.codecool.order_payment.utils.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WarehouseApiClient warehouseApi;
    private final OrderItemCacheService orderItemCacheService;

    @Transactional
    public UUID placeOrder(NewOrderDTO newOrderDTO) {
        PriceRequest priceRequest = createPriceRequest(newOrderDTO);

        PriceResponse prices = warehouseApi.getPrices(priceRequest);

        Set<OrderItem> orderItems = getOrderItems(prices.prices(), newOrderDTO.items());

        Order order = buildOrder(newOrderDTO, orderItems);

        orderItemCacheService.addItemsToCache(newOrderDTO.items());

        return orderRepository.save(order).getPublicID();
    }

    public OrderDTO getOrderByPID(UUID orderPID) {
        return OrderMapper.toDTO(getOrderByPublicID(orderPID));
    }

    protected Order getOrderByPublicID(UUID orderPID) {
        return orderRepository.findByPublicID(orderPID)
                .orElseThrow(
                        () -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND
                                , "Can't find order with Id: " + orderPID
                        )
                );
    }

    private Order buildOrder(NewOrderDTO newOrderDTO, Set<OrderItem> orderItems) {
        LocalDateTime now = LocalDateTime.now();
        BigDecimal orderTotal = getTotalPrice(orderItems);

        return Order.builder()
                .publicID(UUID.randomUUID())
                .userID(newOrderDTO.userID())
                .orderItems(orderItems)
                .total(orderTotal)
                .created(now)
                .updated(now)
                .orderStatus(OrderStatus.RESERVED)
                .build();
    }

    private BigDecimal getTotalPrice(Set<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<OrderItem> getOrderItems(Map<UUID, BigDecimal> prices, Map<UUID, Long> items) {
        return items.keySet().stream()
                .map(
                        itemPID -> createOrderItem(
                                itemPID,
                                items.get(itemPID),
                                prices.get(itemPID)
                        )
                )
                .collect(Collectors.toSet());
    }

    private OrderItem createOrderItem(UUID itemPID, Long amount, BigDecimal itemPrice) {
        return OrderItem.builder()
                .itemPID(itemPID)
                .amount(amount)
                .price(itemPrice)
                .build();
    }

    private PriceRequest createPriceRequest(NewOrderDTO newOrderDTO) {
        List<UUID> itemID = newOrderDTO.items().keySet().stream().toList();
        return new PriceRequest(itemID);
    }
}
