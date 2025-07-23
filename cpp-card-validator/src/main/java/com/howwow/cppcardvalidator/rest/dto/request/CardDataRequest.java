package com.howwow.cppcardvalidator.rest.dto.request;

import com.howwow.cppcardvalidator.validation.annotation.ValidCVV;
import com.howwow.cppcardvalidator.validation.annotation.ValidExpiryDate;
import com.howwow.cppcardvalidator.validation.annotation.ValidLuhn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Данные банковской карты для валидации")
public record CardDataRequest(
        @NotBlank(message = "Номер карты обязателен для заполнения")
        @ValidLuhn
        @Schema(description = "Номер карты (13-19 цифр)",
                example = "4111111111111111",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String cardNumber,

        @NotBlank(message = "Срок действия обязателен для заполнения")
        @ValidExpiryDate
        @Schema(description = "Срок действия в формате MM/YY",
                example = "12/25",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String expiryDate,

        @NotBlank(message = "CVV код обязателен для заполнения")
        @ValidCVV
        @Schema(description = "Код безопасности (3-4 цифры)",
                example = "123",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String cvv
) {
}