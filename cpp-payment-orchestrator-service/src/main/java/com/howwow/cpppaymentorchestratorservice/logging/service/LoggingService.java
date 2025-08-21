package com.howwow.cpppaymentorchestratorservice.logging.service;

import com.howwow.cpppaymentorchestratorservice.logging.dto.request.CreateLogRequest;

public interface LoggingService {
    void sendLog(CreateLogRequest createLogRequest);
}
