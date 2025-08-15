package com.howwow.cppauthrequest.logging.service;

import com.howwow.cppauthrequest.logging.dto.LogDto;

public interface LoggingService {
    void sendLog(LogDto logDto);
}
