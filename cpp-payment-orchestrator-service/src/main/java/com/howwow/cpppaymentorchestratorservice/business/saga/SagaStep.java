package com.howwow.cpppaymentorchestratorservice.business.saga;

public interface SagaStep<T> {
    String getName();

    T execute(SagaContext context);

    void compensate(SagaContext context);
}
