package com.howwow.cpppaymentorchestratorservice.logging.service.impl;

import com.howwow.cpppaymentorchestratorservice.logging.client.LoggingServiceClient;
import com.howwow.cpppaymentorchestratorservice.logging.dto.LogDto;
import com.howwow.cpppaymentorchestratorservice.logging.service.LoggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoggingServiceImpl implements LoggingService {

    private final LoggingServiceClient loggingServiceClient;

    @Async
    @Override
    public void sendLog(LogDto logDto) {
        try {
            loggingServiceClient.createLog(logDto);
        } catch (feign.FeignException fe) {
            log.warn("Feign ошибка при отправке лога ({}): статус={}, причина={}",
                    logDto.service(),
                    fe.status(),
                    fe.getMessage(),
                    fe);
        } catch (Exception e) {
            log.error("Непредвиденная ошибка при отправке лога ({}): {}",
                    logDto.service(),
                    e.getMessage(),
                    e);
        }
    }
}