package com.howwow.cpppaymentorchestratorservice.rest.dto.response;

import com.howwow.cpppaymentorchestratorservice.persistence.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Ответ на запрос авторизации платежа")
public record PaymentAuthorizationResponse(
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
        PaymentStatus status,

        @Schema(
                description = "Дополнительное сообщение о статусе",
                example = "Платеж одобрен",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String message
) {
}