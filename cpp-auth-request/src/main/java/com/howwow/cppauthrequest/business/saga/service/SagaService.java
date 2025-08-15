package com.howwow.cppauthrequest.business.saga.service;

import com.howwow.cppauthrequest.business.saga.SagaStep;

import java.util.List;

public interface SagaService {
    void executeSaga(List<SagaStep<?>> sagaSteps);

    @SuppressWarnings("unchecked")
    <T> T getResult(Class<? extends SagaStep<T>> stepClass);
}
