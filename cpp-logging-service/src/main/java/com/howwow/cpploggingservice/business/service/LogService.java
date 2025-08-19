package com.howwow.cpploggingservice.business.service;

import com.howwow.cpploggingservice.rest.dto.request.CreateLogRequest;
import com.howwow.cpploggingservice.rest.dto.response.CreateLogResponse;
import org.springframework.boot.logging.LogLevel;

import java.time.Instant;
import java.util.List;

public interface LogService {
    CreateLogResponse saveLog(CreateLogRequest createLogRequest);

    List<CreateLogResponse> fetchLogs(LogLevel level,
                                      String service,
                                      String traceId,
                                      Instant from,
                                      Instant to,
                                      int limit);
}
