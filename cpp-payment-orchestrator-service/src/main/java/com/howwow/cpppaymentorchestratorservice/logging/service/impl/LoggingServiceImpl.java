package com.howwow.cpppaymentorchestratorservice.logging.service.impl;

import com.howwow.cpppaymentorchestratorservice.logging.client.LoggingServiceClient;
import com.howwow.cpppaymentorchestratorservice.logging.dto.request.CreateLogRequest;
import com.howwow.cpppaymentorchestratorservice.logging.dto.response.CreateLogResponse;
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

    @Override
    @Async
    public void sendLog(CreateLogRequest createLogRequest) {
        try {
            CreateLogResponse response = loggingServiceClient.createLog(createLogRequest);
            log.info("Лог успешно отправлен в сервис логирования: {}", response.service());
        } catch (feign.FeignException fe) {
            log.warn("Не удалось отправить лог в {}: статус={}, причина={}",
                    createLogRequest.service(),
                    fe.status(),
                    fe.getMessage());
        } catch (Exception e) {
            log.error("Непредвиденная ошибка при отправке лога ({}): {}",
                    createLogRequest.service(),
                    e.getMessage(),
                    e);
        }
    }
}