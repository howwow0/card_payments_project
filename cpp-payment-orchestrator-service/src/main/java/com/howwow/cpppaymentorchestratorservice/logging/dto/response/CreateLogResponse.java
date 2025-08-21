package com.howwow.cpppaymentorchestratorservice.logging.dto.response;

import org.springframework.boot.logging.LogLevel;

import java.time.Instant;

public record CreateLogResponse(
        Instant timestamp,
        LogLevel level,
        String service,
        String message,
        String traceId
) {
}
