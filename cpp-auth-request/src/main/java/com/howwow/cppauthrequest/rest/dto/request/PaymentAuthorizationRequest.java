package com.howwow.cppauthrequest.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Schema(description = "Данные платежа")
public record PaymentAuthorizationRequest(
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
        String email
) {
}
