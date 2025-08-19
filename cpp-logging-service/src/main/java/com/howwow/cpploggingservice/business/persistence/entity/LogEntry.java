package com.howwow.cpploggingservice.business.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.logging.LogLevel;

import java.time.Instant;

@Entity
@Table(name = "logs", schema = "cpp_logging_schema")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private LogLevel level;

    @Column(nullable = false, length = 1000)
    private String message;

    @Column(nullable = false, length = 100)
    private String service;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false, length = 100)
    private String traceId;

    public LogEntry(LogLevel level, String message, Instant timestamp, String traceId, String service) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
        this.traceId = traceId;
        this.service = service;
    }
}
