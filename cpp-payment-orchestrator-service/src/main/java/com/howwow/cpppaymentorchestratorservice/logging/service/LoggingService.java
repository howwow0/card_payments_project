package com.howwow.cpppaymentorchestratorservice.logging.service;

import com.howwow.cpppaymentorchestratorservice.logging.dto.LogDto;

public interface LoggingService {
    void sendLog(LogDto logDto);
}
