package com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.request;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record PaymentProcessRequest(
        BigDecimal amount,
        Currency currency,
        UUID merchantId,
        String email,
        UUID transactionId,
        UUID bankTransactionId,
        Boolean isApproved,
        String reason
) {
}
