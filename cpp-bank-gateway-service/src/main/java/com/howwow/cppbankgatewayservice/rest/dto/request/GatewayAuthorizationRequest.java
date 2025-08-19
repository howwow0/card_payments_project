package com.howwow.cppbankgatewayservice.rest.dto.request;

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
        Currency currency
) {
}
