package com.codecool.checkout.service;

import com.codecool.checkout.dto.DeliveryRequest;
import com.codecool.checkout.dto.OrderStatusChange;
import com.codecool.checkout.modell.jpa.Order;
import com.codecool.checkout.modell.jpa.OrderItem;
import com.codecool.checkout.producer.DeliveryProducer;
import com.codecool.checkout.producer.OrderStatusChangeProducer;
import com.codecool.checkout.utils.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RabbitMQService {
    private final OrderStatusChangeProducer orderStatusChangeProducer;
    private final DeliveryProducer deliveryProducer;
    private final AddressService addressService;

    public void sendOrderChangeMail(Order order) {
        OrderStatusChange orderStatusChange = createOrderStatusChange(order);
        orderStatusChangeProducer.sendOrderStatusChangeMessage(orderStatusChange);
    }

    public void addDelivery(Order order) {
        DeliveryRequest deliveryRequest = constructDeliveryRequest(order);
        deliveryProducer.addDeliveryToQueue(deliveryRequest);
    }

    private DeliveryRequest constructDeliveryRequest(Order order) {
        Map<UUID, Long> itemMap = extractItemPIDAndAmount(order.getOrderItems());
        UUID addressPID = addressService.getUserPreferredAddressPID(order.getUserID());
        UUID orderPID = order.getPublicID();
        return new DeliveryRequest(
                itemMap,
                addressPID,
                orderPID,
                order.getOrderStatus()
        );
    }

    private OrderStatusChange createOrderStatusChange(Order order) {
        return new OrderStatusChange(
                order.getEmail(),
                order.getName(),
                OrderMapper.toDTO(order)
        );
    }

    private Map<UUID, Long> extractItemPIDAndAmount(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .collect(
                        Collectors.toMap(
                                OrderItem::getItemPID,
                                OrderItem::getAmount
                        )
                );
    }

}
