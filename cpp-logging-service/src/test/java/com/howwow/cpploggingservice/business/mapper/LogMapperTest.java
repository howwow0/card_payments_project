package com.howwow.cpploggingservice.business.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.howwow.cpploggingservice.business.persistence.entity.LogEntry;
import com.howwow.cpploggingservice.rest.dto.request.CreateLogRequest;
import com.howwow.cpploggingservice.rest.dto.response.CreateLogResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.logging.LogLevel;

import java.time.Instant;
import java.util.List;


class LogMapperTest {

    private final LogMapper mapper = new LogMapper();

    @Test
    void testAsLogEntry() {
        CreateLogRequest request = new CreateLogRequest(
                Instant.parse("2025-08-19T12:00:00Z"),
                LogLevel.INFO,
                "user-service",
                "Test message",
                "trace123"
        );

        LogEntry logEntry = mapper.asLogEntry(request);

        assertEquals(request.timestamp(), logEntry.getTimestamp());
        assertEquals(request.level(), logEntry.getLevel());
        assertEquals(request.service(), logEntry.getService());
        assertEquals(request.message(), logEntry.getMessage());
        assertEquals(request.traceId(), logEntry.getTraceId());
    }

    @Test
    void testAsCreateLogResponse() {
        LogEntry logEntry = new LogEntry(LogLevel.INFO,
                "Test message",
                Instant.parse("2025-08-19T12:00:00Z"),
                "trace123",
                "user-service"
        );

        CreateLogResponse response = mapper.asCreateLogResponse(logEntry);

        assertEquals(logEntry.getTimestamp(), response.timestamp());
        assertEquals(logEntry.getLevel(), response.level());
        assertEquals(logEntry.getService(), response.service());
        assertEquals(logEntry.getMessage(), response.message());
        assertEquals(logEntry.getTraceId(), response.traceId());
    }

    @Test
    void testAsCreateLogResponseList() {
        LogEntry logEntry1 = new LogEntry(LogLevel.INFO, "Msg1", Instant.parse("2025-08-19T12:00:00Z"), "trace1", "service1");
        LogEntry logEntry2 = new LogEntry(LogLevel.ERROR, "Msg2", Instant.parse("2025-08-19T13:00:00Z"), "trace2", "service2");

        List<CreateLogResponse> responses = mapper.asCreateLogResponseList(List.of(logEntry1, logEntry2));

        assertEquals(2, responses.size());
        assertEquals("Msg1", responses.get(0).message());
        assertEquals("Msg2", responses.get(1).message());
    }
}
