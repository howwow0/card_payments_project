package com.howwow.cpploggingservice.business.service.impl;

import com.howwow.cpploggingservice.business.mapper.LogMapper;
import com.howwow.cpploggingservice.business.persistence.entity.LogEntry;
import com.howwow.cpploggingservice.business.persistence.repository.LogEntryRepository;
import com.howwow.cpploggingservice.business.service.LogService;
import com.howwow.cpploggingservice.rest.dto.LogDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LogServiceImpl implements LogService {
    private final LogEntryRepository logEntryRepository;
    private final LogMapper logMapper;

    @Override
    public LogDto saveLog(LogDto logDto) {
        LogEntry logEntry = logMapper.asLogEntry(logDto);
        LogEntry savedLogEntry = logEntryRepository.save(logEntry);
        log.info("[{}] [{}] [{}] [{}] {}",
                savedLogEntry.getTimestamp(),
                savedLogEntry.getLevel(),
                savedLogEntry.getService(),
                savedLogEntry.getTraceId(),
                savedLogEntry.getMessage());
        return logMapper.asLogDto(savedLogEntry);
    }

    @Override
    public List<LogDto> fetchLogs(LogLevel level, String service, String traceId, Instant from, Instant to, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<LogEntry> entries = logEntryRepository.findLogEntriesByFilters(level,
                service,
                traceId,
                from == null ? null : Timestamp.from(from),
                to == null ? null : Timestamp.from(to),
                pageable);

        return logMapper.asLogDtoList(entries);
    }
}
