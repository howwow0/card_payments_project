package com.howwow.cpploggingservice.business.service;

import com.howwow.cpploggingservice.rest.dto.LogDto;
import org.springframework.boot.logging.LogLevel;

import java.time.Instant;
import java.util.List;

public interface LogService {
    LogDto saveLog(LogDto logDto);

    List<LogDto> fetchLogs(LogLevel level,
                           String service,
                           String traceId,
                           Instant from,
                           Instant to,
                           int limit);
}
