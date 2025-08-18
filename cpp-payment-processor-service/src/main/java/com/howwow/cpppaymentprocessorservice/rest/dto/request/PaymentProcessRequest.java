package com.howwow.cpppaymentprocessorservice.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Schema(description = "Данные платежа")
public record PaymentProcessRequest(
        @NotNull(message = "Сумма платежа обязательна для заполнения")
        @DecimalMin(message = "Сумма платежа не может быть меньше 0", value = "0")
        @Schema(description = "Сумма платежа",
                example = "100.0",
                requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal amount,

        @NotNull(message = "Валюта обязательна для заполнения")
        @Schema(description = "Валюта платежа",
                example = "RUB",
                requiredMode = Schema.RequiredMode.REQUIRED)
        Currency currency,

        @NotNull(message = "Идентификатор продавца обязателен для заполнения")
        @Schema(description = "Идентификатор продавца",
                example = "5ae07e55-0aea-4332-b741-96a07d1d6a6c",
                requiredMode = Schema.RequiredMode.REQUIRED)
        UUID merchantId,

        @NotNull(message = "Электронная почта покупателя обязательна для заполнения")
        @Email(message = "Введите корректный адрес электронной почты")
        @Schema(
                description = "Адрес электронной почты покупателя",
                example = "customer@example.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @NotNull(message = "Идентификатор транзакции обязателен для заполнения")
        @Schema(
                description = "Уникальный идентификатор платежа",
                example = "123e4567-e89b-12d3-a456-426614174000",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID transactionId,

        @NotNull(message = "Идентификатор транзакции банка обязателен для заполнения")
        @Schema(
                description = "Уникальный идентификатор транзакции в банковской системе",
                example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID bankTransactionId,

        @NotNull(message = "Статус авторизации обязателен для заполнения")
        @Schema(
                description = "Статус авторизации платежа",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Boolean isApproved,

        @NotBlank(message = "Причина не может быть пустой")
        @Schema(
                description = "Причина отказа/одобрения",
                example = "Недостаточно средств",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String reason
) {
}
