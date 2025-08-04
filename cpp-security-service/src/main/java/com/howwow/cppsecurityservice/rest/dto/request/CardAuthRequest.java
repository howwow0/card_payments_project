package com.howwow.cppsecurityservice.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Данные карты")
public record CardAuthRequest(
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
        @Size(min = 3, max = 4, message = "CVV код должен состоять из 3 или 4 цифр")
        String cvv
) {
}
