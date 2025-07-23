package com.howwow.cppcardvalidator.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Результат проверки карты")
public record CardValidationResponse(
        @Schema(
                description = "Результат валидации",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        boolean isValid,

        @Schema(
                description = "Детальное сообщение о результате проверки",
                example = "Карта успешно проверена",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String message
) {
    public static CardValidationResponse success() {
        return new CardValidationResponse(true, "Карта успешно проверена");
    }

    public static CardValidationResponse failure(String errorMessage) {
        return new CardValidationResponse(false, errorMessage);
    }
}