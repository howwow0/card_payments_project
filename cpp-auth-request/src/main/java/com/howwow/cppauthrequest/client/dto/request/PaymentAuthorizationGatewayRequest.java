package com.howwow.cppauthrequest.client.dto.request;

import java.math.BigDecimal;
import java.util.Currency;

public record PaymentAuthorizationGatewayRequest(
        BigDecimal amount,
        Currency currency,
        String cardNumber,
        String expiryDate,
        String cvv
) {
}
