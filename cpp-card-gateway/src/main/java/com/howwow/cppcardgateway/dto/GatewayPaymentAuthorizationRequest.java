package com.howwow.cppcardgateway.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record GatewayPaymentAuthorizationRequest(
        BigDecimal amount,
        Currency currency,
        String cardNumber,
        String expiryDate,
        String cvv,
        UUID merchantId,
        String email
) {
}