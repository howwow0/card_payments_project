package com.howwow.cpppaymentprocessorservice.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Ответ на запрос авторизации платежа")
public record PaymentProcessResponse(
        @Schema(
                description = "Уникальный идентификатор платежа",
                example = "123e4567-e89b-12d3-a456-426614174000",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID transactionId,

        @Schema(
                description = "Уникальный идентификатор транзакции",
                example = "123e4567-e89b-12d3-a456-426614174000",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID bankTransactionId,

        @Schema(
                description = "Статус авторизации платежа",
                example = "APPROVED",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String status,

        @Schema(
                description = "Дополнительное сообщение о статусе",
                example = "Платеж одобрен",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String message
) {
}