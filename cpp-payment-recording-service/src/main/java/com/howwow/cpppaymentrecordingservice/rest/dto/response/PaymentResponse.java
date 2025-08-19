package com.howwow.cpppaymentrecordingservice.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Ответ при получении платежа")
public record PaymentResponse(

        @Schema(description = "ID платежа в БД", example = "1")
        UUID id,

        @Schema(description = "ID транзакции в системе", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID transactionId,

        @Schema(description = "ID транзакции в банке", example = "660e8400-e29b-41d4-a716-446655440111")
        UUID bankTransactionId,

        @Schema(description = "ID продавца", example = "770e8400-e29b-41d4-a716-446655440222")
        UUID merchantId,

        @Schema(description = "Email клиента", example = "customer@example.com")
        String email,

        @Schema(description = "Сумма платежа", example = "1500.75")
        BigDecimal amount,

        @Schema(description = "Валюта", example = "USD")
        String currency,

        @Schema(description = "Статус одобрения платежа", example = "true")
        Boolean approved,

        @Schema(description = "Причина отказа (если есть)", example = "Недостаточно средств")
        String reason,

        @Schema(description = "Время создания записи", example = "2025-08-19T12:34:56Z")
        Instant createdAt
) {}
