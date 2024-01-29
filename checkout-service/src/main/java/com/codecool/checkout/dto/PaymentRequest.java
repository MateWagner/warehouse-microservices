package com.codecool.checkout.dto;

import java.util.UUID;

public record PaymentRequest(String cardNumber, String exp, Integer cv, String nameOnCard, UUID orderPID) {
}
