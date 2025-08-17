package com.howwow.cppauthrequest.logging.saga;

import com.howwow.cppauthrequest.business.TransactionIdGenerationStep;
import com.howwow.cppauthrequest.business.saga.SagaContext;
import com.howwow.cppauthrequest.business.saga.SagaStep;
import com.howwow.cppauthrequest.logging.dto.LogDto;
import com.howwow.cppauthrequest.logging.service.LoggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingPaymentStep implements SagaStep<Void> {

    private final LoggingService loggingService;

    @Override
    public String getName() {
        return "LoggingPaymentStep";
    }

    @Override
    public Void execute(SagaContext context) {
        String transactionId = context.get(TransactionIdGenerationStep.class);
        LogDto logDto = new LogDto(
                Instant.now(),
                LogLevel.INFO,
                "auth-request-service",
                String.format("""
                        Инициализация платежа:
                            - TransactionId: %s
                        """, transactionId),
                transactionId);
        loggingService.sendLog(logDto);
        return null;
    }

    @Override
    public void compensate(SagaContext context) {
        log.warn("Компенсация шага LoggingPaymentStep не требуется. Лог уже отправлен");
    }

}
