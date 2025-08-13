package com.howwow.cpploggingservice.business.persistence.repository;

import com.howwow.cpploggingservice.business.persistence.entity.LogEntry;
import org.springframework.boot.logging.LogLevel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    @Query("""
             SELECT l FROM LogEntry l
             WHERE (:level IS NULL OR l.level = :level)
               AND (:service IS NULL OR l.service = :service)
               AND (:traceId IS NULL OR l.traceId = :traceId)
              AND (cast(:from as timestamp) IS NULL OR l.timestamp >= :from)
              AND (cast(:to as timestamp) IS NULL OR l.timestamp <= :to)
             ORDER BY l.timestamp DESC
            """)
    List<LogEntry> findLogEntriesByFilters(
            @Param("level") LogLevel level,
            @Param("service") String service,
            @Param("traceId") String traceId,
            @Param("from") Timestamp from,
            @Param("to") Timestamp to,
            Pageable pageable
    );

}
