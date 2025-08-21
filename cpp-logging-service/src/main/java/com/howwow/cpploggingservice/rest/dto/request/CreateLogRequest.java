package com.howwow.cpploggingservice.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.boot.logging.LogLevel;

import java.time.LocalDateTime;

@Schema(description = "Данные лога")
public record CreateLogRequest(
        @NotNull(message = "Временная метка не может быть пустой")
        @Schema(description = "Временная метка лог-сообщения", example = "2025-08-10T12:34:56", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime timestamp,

        @NotNull(message = "Уровень лога не может быть пустым")
        @Schema(description = "Уровень лог-сообщения (например, INFO, WARN, ERROR)", example = "INFO", requiredMode = Schema.RequiredMode.REQUIRED)
        LogLevel level,

        @NotBlank(message = "Имя сервиса не может быть пустым")
        @Size(max = 100, message = "Имя сервиса не может быть длиннее 100 символов")
        @Schema(description = "Имя сервиса, откуда поступило лог-сообщение", example = "user-service", requiredMode = Schema.RequiredMode.REQUIRED)
        String service,

        @NotBlank(message = "Сообщение лога не может быть пустым")
        @Size(max = 1000, message = "Сообщение лога не может превышать 1000 символов")
        @Schema(description = "Основное сообщение лога", example = "User not found", requiredMode = Schema.RequiredMode.REQUIRED)
        String message,

        @NotBlank(message = "Идентификатор трассировки запроса не может быть пустым")
        @Size(max = 100, message = "Идентификатор трассировки запроса не может быть длиннее 100 символов")
        @Schema(description = "Идентификатор трассировки запроса", example = "abc123", requiredMode = Schema.RequiredMode.REQUIRED)
        String traceId
) {
}
