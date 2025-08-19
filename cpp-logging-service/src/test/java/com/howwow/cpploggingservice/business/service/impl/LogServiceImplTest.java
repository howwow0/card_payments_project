package com.howwow.cpploggingservice.business.service.impl;


import com.howwow.cpploggingservice.business.mapper.LogMapper;
import com.howwow.cpploggingservice.business.persistence.entity.LogEntry;
import com.howwow.cpploggingservice.business.persistence.repository.LogEntryRepository;
import com.howwow.cpploggingservice.rest.dto.request.CreateLogRequest;
import com.howwow.cpploggingservice.rest.dto.response.CreateLogResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.logging.LogLevel;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogServiceImplTest {

    @Mock
    private LogEntryRepository logEntryRepository;

    @Mock
    private LogMapper logMapper;

    @InjectMocks
    private LogServiceImpl logService;

    private CreateLogRequest createLogRequest;
    private LogEntry logEntry;
    private CreateLogResponse createLogResponse;

    @BeforeEach
    void setUp() {

        createLogRequest = new CreateLogRequest(
                Instant.parse("2025-08-10T12:34:56Z"),
                LogLevel.INFO,
                "user-service",
                "User not found",
                "abc123"
        );

        logEntry = new LogEntry();
        logEntry.setTimestamp(createLogRequest.timestamp());
        logEntry.setLevel(createLogRequest.level());
        logEntry.setService(createLogRequest.service());
        logEntry.setMessage(createLogRequest.message());
        logEntry.setTraceId(createLogRequest.traceId());

        createLogResponse = new CreateLogResponse(
                logEntry.getTimestamp(),
                logEntry.getLevel(),
                logEntry.getService(),
                logEntry.getMessage(),
                logEntry.getTraceId()
        );
    }

    @Test
    void testSaveLog() {
        when(logMapper.asLogEntry(createLogRequest)).thenReturn(logEntry);
        when(logEntryRepository.save(logEntry)).thenReturn(logEntry);
        when(logMapper.asCreateLogResponse(logEntry)).thenReturn(createLogResponse);

        CreateLogResponse result = logService.saveLog(createLogRequest);

        assertEquals(createLogResponse, result);

        verify(logMapper).asLogEntry(createLogRequest);
        verify(logEntryRepository).save(logEntry);
        verify(logMapper).asCreateLogResponse(logEntry);
    }

    @Test
    void testFetchLogs() {
        List<LogEntry> entries = List.of(logEntry);
        List<CreateLogResponse> responses = List.of(createLogResponse);

        Instant from = Instant.parse("2025-08-01T00:00:00Z");
        Instant to = Instant.parse("2025-08-31T23:59:59Z");

        when(logEntryRepository.findLogEntriesByFilters(
                eq(LogLevel.INFO),
                eq("user-service"),
                eq("abc123"),
                eq(from),
                eq(to),
                any(PageRequest.class)))
                .thenReturn(entries);
        when(logMapper.asCreateLogResponseList(entries)).thenReturn(responses);

        List<CreateLogResponse> result = logService.fetchLogs(
                LogLevel.INFO, "user-service", "abc123",
                Instant.parse("2025-08-01T00:00:00Z"),
                Instant.parse("2025-08-31T23:59:59Z"),
                10
        );

        assertEquals(responses, result);

        verify(logEntryRepository).findLogEntriesByFilters(
                eq(LogLevel.INFO),
                eq("user-service"),
                eq("abc123"),
                eq(from),
                eq(to),
                any(PageRequest.class)
        );
        verify(logMapper).asCreateLogResponseList(entries);
    }
}
