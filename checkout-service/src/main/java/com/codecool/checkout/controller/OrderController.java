package com.codecool.checkout.controller;

import com.codecool.checkout.dto.NewOrderDTO;
import com.codecool.checkout.dto.OrderDTO;
import com.codecool.checkout.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID placeOrder(@RequestBody NewOrderDTO newOrderDTO) {
        return orderService.placeOrder(newOrderDTO);
    }

    @GetMapping("{orderPID}")
    public OrderDTO getOrderByPID(@PathVariable UUID orderPID) {
        return orderService.getOrderByPID(orderPID);
    }
}
