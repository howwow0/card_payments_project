package com.howwow.cppcardgateway.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record PaymentAuthorizationRequest(
        BigDecimal amount,
        Currency currency,
        UUID merchantId,
        String email
) {
}
