package com.codecool.checkout.api.dto;

import java.math.BigDecimal;

public record PaymentApiRequest(String cardNumber, String exp, int cv, String nameOnCard, BigDecimal total) {
}
