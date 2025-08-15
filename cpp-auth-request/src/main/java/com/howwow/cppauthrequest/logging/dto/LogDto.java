package com.howwow.cppauthrequest.logging.dto;

import org.springframework.boot.logging.LogLevel;

import java.time.Instant;

public record LogDto(
        Instant timestamp,
        LogLevel level,
        String service,
        String message,
        String traceId
) {
}
