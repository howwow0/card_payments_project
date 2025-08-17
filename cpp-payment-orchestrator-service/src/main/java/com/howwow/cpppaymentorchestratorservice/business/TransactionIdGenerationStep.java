package com.howwow.cpppaymentorchestratorservice.business;

import com.howwow.cpppaymentorchestratorservice.business.saga.SagaContext;
import com.howwow.cpppaymentorchestratorservice.business.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class TransactionIdGenerationStep implements SagaStep<String> {

    @Override
    public String getName() {
        return "TransactionIdGenerationStep";
    }

    @Override
    public String execute(SagaContext context) {
        String transactionId = UUID.randomUUID().toString();
        log.info("Сгенерирован ID транзакции: {}", transactionId);
        return transactionId;
    }

    @Override
    public void compensate(SagaContext context) {
        log.info("Компенсация не требуется для шага генерации ID транзакции");
    }
}
