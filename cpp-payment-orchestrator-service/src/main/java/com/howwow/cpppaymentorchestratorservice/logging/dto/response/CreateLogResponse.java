package com.howwow.cpppaymentorchestratorservice.logging.dto.response;

import org.springframework.boot.logging.LogLevel;

import java.time.LocalDateTime;

public record CreateLogResponse(
        LocalDateTime timestamp,
        LogLevel level,
        String service,
        String message,
        String traceId
) {
}
