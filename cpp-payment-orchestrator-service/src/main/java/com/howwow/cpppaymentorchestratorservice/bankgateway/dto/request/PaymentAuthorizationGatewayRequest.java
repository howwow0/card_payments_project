package com.howwow.cpppaymentorchestratorservice.bankgateway.dto.request;

import java.math.BigDecimal;
import java.util.Currency;

public record PaymentAuthorizationGatewayRequest(
        BigDecimal amount,
        Currency currency
) {
}
