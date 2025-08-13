package com.howwow.cpploggingservice.business.mapper;

import com.howwow.cpploggingservice.business.persistence.entity.LogEntry;
import com.howwow.cpploggingservice.rest.dto.LogDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogMapper {

    public LogDto asLogDto(LogEntry log) {
        return new LogDto(log.getTimestamp(), log.getLevel(), log.getService(), log.getMessage(), log.getTraceId());
    }

    public List<LogDto> asLogDtoList(List<LogEntry> logs) {
        return logs.stream()
                .map(this::asLogDto)
                .toList();
    }

    public LogEntry asLogEntry(LogDto logDto) {
        return new LogEntry(logDto.level(), logDto.message(), logDto.timestamp(), logDto.traceId(), logDto.service());
    }
}
