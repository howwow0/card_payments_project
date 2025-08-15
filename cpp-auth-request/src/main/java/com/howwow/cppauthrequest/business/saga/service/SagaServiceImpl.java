package com.howwow.cppauthrequest.business.saga.service;

import com.howwow.cppauthrequest.business.saga.SagaContext;
import com.howwow.cppauthrequest.business.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SagaServiceImpl implements SagaService {

    private final SagaContext sagaContext;

    @SuppressWarnings("unchecked")
    @Override
    public void executeSaga(List<SagaStep<?>> sagaSteps) {
        List<SagaStep<?>> executedSteps = new ArrayList<>();

        for (SagaStep<?> step : sagaSteps) {
            try {
                Object result = step.execute(sagaContext);
                sagaContext.put((Class<SagaStep<Object>>) step.getClass(), result);
                executedSteps.add(step);
                log.info("Шаг '{}' выполнен успешно", step.getName());
            } catch (Exception e) {
                log.error("Ошибка в шаге '{}': {}", step.getName(), e.getMessage());
                for (int i = executedSteps.size() - 1; i >= 0; i--) {
                    try {
                        executedSteps.get(i).compensate(sagaContext);
                        log.info("Шаг '{}' успешно откатан", executedSteps.get(i).getName());
                    } catch (Exception ce) {
                        log.error("Ошибка при откате шага '{}': {}", executedSteps.get(i).getName(), ce.getMessage());
                    }
                }
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getResult(Class<? extends SagaStep<T>> stepClass) {
        return (T) sagaContext.get(stepClass);
    }
}
