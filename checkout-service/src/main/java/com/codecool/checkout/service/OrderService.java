package com.codecool.checkout.service;

import com.codecool.checkout.api.WarehouseApiClient;
import com.codecool.checkout.api.dto.PriceRequest;
import com.codecool.checkout.api.dto.PriceResponse;
import com.codecool.checkout.data.OrderStatus;
import com.codecool.checkout.dto.NewOrderDTO;
import com.codecool.checkout.dto.OrderDTO;
import com.codecool.checkout.modell.jpa.Order;
import com.codecool.checkout.modell.jpa.OrderItem;
import com.codecool.checkout.repository.jpa.OrderRepository;
import com.codecool.checkout.utils.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final RabbitMQService rabbitMQService;
    private final TokenClaimService tokenClaimService;

    @Transactional
    public UUID placeOrder(NewOrderDTO newOrderDTO) {
        PriceRequest priceRequest = createPriceRequest(newOrderDTO.items());
        PriceResponse prices = warehouseApi.getPrices(priceRequest);

        Set<OrderItem> orderItems = getOrderItems(prices.prices(), newOrderDTO.items());

        Order order = buildOrder(newOrderDTO, orderItems);

        orderItemCacheService.addItemsToCache(newOrderDTO.items());

        Order savedOrder = orderRepository.save(order);

        rabbitMQService.sendOrderChangeMail(savedOrder);

        return savedOrder.getPublicID();
    }

    public ResponseEntity<String> confirmDelivery(UUID orderPID) {
        System.out.println(orderPID);
        OrderStatus delivered = OrderStatus.DELIVERED;
        changeOrderStatus(delivered, orderPID);
        Order order = getOrderByPublicID(orderPID);
        Map<UUID, Long> itemMap = order.getOrderItems().stream().collect(Collectors.toMap(OrderItem::getItemPID, OrderItem::getAmount));
        orderItemCacheService.removeItemsFromCache(itemMap);
        return ResponseEntity.ok("ok");
    }

    public OrderDTO getOrderDTOByPID(UUID orderPID) {
        return OrderMapper.toDTO(getOrderByPublicID(orderPID));
    }

    protected void changeOrderStatus(OrderStatus orderStatus, UUID orderPID) {
        LocalDateTime now = LocalDateTime.now();
        Order order = getOrderByPublicID(orderPID);
        order.setOrderStatus(orderStatus);
        order.setUpdated(now);
        orderRepository.save(order);
        rabbitMQService.sendOrderChangeMail(order);
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
                .email(tokenClaimService.getCurrentUserEmail())
                .name(tokenClaimService.getCurrentUserName())
                .build();
    }

    private BigDecimal getTotalPrice(Set<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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
                .unitPrice(itemPrice)
                .build();
    }

    private PriceRequest createPriceRequest(Map<UUID, Long> items) {
        List<UUID> itemID = items.keySet().stream().toList();
        return new PriceRequest(itemID);
    }

}
