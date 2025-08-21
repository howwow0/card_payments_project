package com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.response;


import java.util.UUID;

public record PaymentProcessResponse(
        UUID transactionId,
        UUID bankTransactionId,
        String status,
        String message
) {
}