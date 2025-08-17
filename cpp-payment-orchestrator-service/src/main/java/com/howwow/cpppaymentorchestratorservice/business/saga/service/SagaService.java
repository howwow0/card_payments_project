package com.howwow.cpppaymentorchestratorservice.business.saga.service;

import com.howwow.cpppaymentorchestratorservice.business.saga.SagaStep;

import java.util.List;

public interface SagaService {
    void executeSaga(List<SagaStep<?>> sagaSteps);

    @SuppressWarnings("unchecked")
    <T> T getResult(Class<? extends SagaStep<T>> stepClass);
}
