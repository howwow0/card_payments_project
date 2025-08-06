package com.howwow.cppbankgateway.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Ответ банка-эмитента на запрос авторизации платежа")
public record GatewayAuthorizationResponse(
        @Schema(
                description = "Уникальный идентификатор транзакции в банковской системе",
                example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID bankTransactionId,

        @Schema(
                description = "Статус авторизации платежа",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        boolean isApproved,

        @Schema(
                description = "Причина отказа/одобрения",
                example = "Недостаточно средств",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String reason
) {
    public static GatewayAuthorizationResponse approved(UUID bankTransactionId, String message) {
        return new GatewayAuthorizationResponse(bankTransactionId, true, message);
    }

    public static GatewayAuthorizationResponse rejected(UUID bankTransactionId, String message) {
        return new GatewayAuthorizationResponse(bankTransactionId, false, message);
    }
}