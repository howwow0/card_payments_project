package com.howwow.cpppaymentorchestratorservice.logging.dto.request;

import org.springframework.boot.logging.LogLevel;

import java.time.LocalDateTime;

public record CreateLogRequest(
        LocalDateTime timestamp,
        LogLevel level,
        String service,
        String message,
        String traceId
) {
}
