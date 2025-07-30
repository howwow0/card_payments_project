package com.howwow.cppbankgateway.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

@Schema(description = "Данные платежа")
public record GatewayAuthorizationRequest(
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

        @NotBlank(message = "Номер карты обязателен для заполнения")
        @Schema(description = "Номер карты (13-19 цифр)",
                example = "4111111111111111",
                requiredMode = Schema.RequiredMode.REQUIRED)

        String cardNumber,

        @NotBlank(message = "Срок действия обязателен для заполнения")
        @Schema(description = "Срок действия в формате MM/YY",
                example = "12/25",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String expiryDate,

        @NotBlank(message = "CVV код обязателен для заполнения")
        @Schema(description = "Код безопасности (3-4 цифры)",
                example = "123",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String cvv
) {
}
