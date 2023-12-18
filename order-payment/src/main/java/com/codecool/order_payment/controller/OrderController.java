package com.codecool.order_payment.controller;

import com.codecool.order_payment.dto.NewOrderDTO;
import com.codecool.order_payment.dto.OrderDTO;
import com.codecool.order_payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public UUID placeOrder(@RequestBody NewOrderDTO newOrderDTO) {
        return orderService.placeOrder(newOrderDTO);
    }

    @GetMapping("{orderPID}")
    public OrderDTO getOrderByPID(@PathVariable UUID orderPID) {
        return orderService.getOrderByPID(orderPID);
    }
}
