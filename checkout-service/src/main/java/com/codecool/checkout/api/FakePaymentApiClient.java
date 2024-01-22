package com.codecool.checkout.api;

import com.codecool.checkout.api.dto.PaymentApiRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class FakePaymentApiClient implements PaymentApiClient {
    private boolean isRejected = false;

    @Override
    public void sendPaymentRequest(PaymentApiRequest paymentApiRequest) {
        isRejected = !isRejected;

        if (isRejected) {
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST,
                    "Payment failed as intended"
            );
        }
    }
}
