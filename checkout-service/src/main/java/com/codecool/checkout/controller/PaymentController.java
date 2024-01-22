package com.codecool.checkout.controller;

import com.codecool.checkout.dto.PaymentRequest;
import com.codecool.checkout.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public void pay(@RequestBody PaymentRequest paymentRequest) {
        paymentService.payment(paymentRequest);
    }
}
