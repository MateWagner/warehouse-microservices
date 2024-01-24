package com.codecool.checkout.service;

import com.codecool.checkout.api.PaymentApiClient;
import com.codecool.checkout.api.dto.PaymentApiRequest;
import com.codecool.checkout.data.OrderStatus;
import com.codecool.checkout.dto.PaymentRequest;
import com.codecool.checkout.modell.jpa.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final OrderService orderService;
    private final PaymentApiClient paymentApiClient;
    private final RabbitMQService mqService;

    @Transactional
    public void payment(PaymentRequest paymentRequest) {
        UUID orderPID = paymentRequest.orderPID();
        Order order = orderService.getOrderByPublicID(orderPID);

        BigDecimal total = order.getTotal();

        PaymentApiRequest paymentApiRequest = createPaymentApiRequest(paymentRequest, total);

        paymentApiClient.sendPaymentRequest(paymentApiRequest);

        orderService.changeOrderStatus(OrderStatus.PAID, orderPID);
        mqService.addDelivery(order);
    }


    private static PaymentApiRequest createPaymentApiRequest(PaymentRequest paymentRequest, BigDecimal total) {
        return new PaymentApiRequest(
                paymentRequest.cardNumber(),
                paymentRequest.exp(),
                paymentRequest.cv(),
                paymentRequest.nameOnCard(),
                total
        );
    }


}
