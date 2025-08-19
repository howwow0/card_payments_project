package com.howwow.cpploggingservice.business.mapper;

import com.howwow.cpploggingservice.business.persistence.entity.LogEntry;
import com.howwow.cpploggingservice.rest.dto.request.CreateLogRequest;
import com.howwow.cpploggingservice.rest.dto.response.CreateLogResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogMapper {

    public CreateLogResponse asCreateLogResponse(LogEntry log) {
        return new CreateLogResponse(log.getTimestamp(), log.getLevel(), log.getService(), log.getMessage(), log.getTraceId());
    }

    public List<CreateLogResponse> asCreateLogResponseList(List<LogEntry> logs) {
        return logs.stream()
                .map(this::asCreateLogResponse)
                .toList();
    }

    public LogEntry asLogEntry(CreateLogRequest createLogRequest) {
        return new LogEntry(createLogRequest.level(), createLogRequest.message(), createLogRequest.timestamp(), createLogRequest.traceId(), createLogRequest.service());
    }
}
