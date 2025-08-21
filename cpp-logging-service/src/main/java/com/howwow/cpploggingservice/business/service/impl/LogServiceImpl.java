package com.howwow.cpploggingservice.business.service.impl;

import com.howwow.cpploggingservice.business.mapper.LogMapper;
import com.howwow.cpploggingservice.business.persistence.entity.LogEntry;
import com.howwow.cpploggingservice.business.persistence.repository.LogEntryRepository;
import com.howwow.cpploggingservice.business.service.LogService;
import com.howwow.cpploggingservice.rest.dto.request.CreateLogRequest;
import com.howwow.cpploggingservice.rest.dto.response.CreateLogResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LogServiceImpl implements LogService {
    private final LogEntryRepository logEntryRepository;
    private final LogMapper logMapper;

    @Override
    public CreateLogResponse saveLog(CreateLogRequest createLogRequest) {
        LogEntry logEntry = logMapper.asLogEntry(createLogRequest);
        LogEntry savedLogEntry = logEntryRepository.save(logEntry);
        log.info("[{}] [{}] [{}] [{}] {}",
                savedLogEntry.getTimestamp(),
                savedLogEntry.getLevel(),
                savedLogEntry.getService(),
                savedLogEntry.getTraceId(),
                savedLogEntry.getMessage());
        return logMapper.asCreateLogResponse(savedLogEntry);
    }

    @Override
    public List<CreateLogResponse> fetchLogs(LogLevel level, String service, String traceId, LocalDateTime from, LocalDateTime to, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<LogEntry> entries = logEntryRepository.findLogEntriesByFilters(level, service, traceId, from, to, pageable);
        return logMapper.asCreateLogResponseList(entries);
    }
}
