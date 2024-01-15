package com.codecool.messageservice.service;

import com.codecool.messageservice.consumer.dto.OrderDTO;
import com.codecool.messageservice.consumer.dto.OrderStatusChange;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendOrderStatusMail(OrderStatusChange orderStatusChange) {
        OrderDTO order = orderStatusChange.order();

        final SimpleMailMessage simpleMailMessage = getStatusMail(orderStatusChange, order);

        javaMailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage getStatusMail(OrderStatusChange orderStatusChange, OrderDTO order) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(orderStatusChange.email());
        simpleMailMessage.setSubject("Warehouse Microservice order Status: " + order.orderStatus().name());
        simpleMailMessage.setText("Dear " + orderStatusChange.name() + ",\n\n  Your order status: " + order.orderStatus().name() + "\n Order price: " + order.total());
        return simpleMailMessage;
    }

}
