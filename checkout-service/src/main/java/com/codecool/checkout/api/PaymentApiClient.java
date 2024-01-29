package com.codecool.checkout.api;

import com.codecool.checkout.api.dto.PaymentApiRequest;

public interface PaymentApiClient {
    void sendPaymentRequest(PaymentApiRequest paymentApiRequest);
}
