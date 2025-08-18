package com.howwow.event;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record PaymentEvent(
        UUID transactionId,
        UUID bankTransactionId,
        UUID merchantId,
        BigDecimal amount,
        Currency currency,
        String email,
        Boolean isApproved,
        String reason
) {
}

