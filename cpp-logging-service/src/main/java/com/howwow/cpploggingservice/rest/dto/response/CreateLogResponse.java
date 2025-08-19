package com.howwow.cpploggingservice.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.logging.LogLevel;

import java.time.Instant;

@Schema(description = "Данные лога")
public record CreateLogResponse(
        @Schema(description = "Временная метка лог-сообщения", example = "2025-08-10T12:34:56", requiredMode = Schema.RequiredMode.REQUIRED)
        Instant timestamp,

        @Schema(description = "Уровень лог-сообщения (например, INFO, WARN, ERROR)", example = "INFO", requiredMode = Schema.RequiredMode.REQUIRED)
        LogLevel level,

        @Schema(description = "Имя сервиса, откуда поступило лог-сообщение", example = "user-service", requiredMode = Schema.RequiredMode.REQUIRED)
        String service,

        @Schema(description = "Основное сообщение лога", example = "User not found", requiredMode = Schema.RequiredMode.REQUIRED)
        String message,

        @Schema(description = "Идентификатор трассировки запроса", example = "abc123", requiredMode = Schema.RequiredMode.REQUIRED)
        String traceId
) {
}
