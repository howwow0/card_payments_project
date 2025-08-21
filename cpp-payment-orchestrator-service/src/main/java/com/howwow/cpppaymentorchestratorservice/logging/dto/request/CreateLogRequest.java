package com.howwow.cpppaymentorchestratorservice.logging.dto.request;

import org.springframework.boot.logging.LogLevel;

import java.time.Instant;

public record CreateLogRequest(
        Instant timestamp,
        LogLevel level,
        String service,
        String message,
        String traceId
) {
}
