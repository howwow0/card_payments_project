package com.howwow.cppauthrequest.business.saga;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SagaContext {
    private final Map<Class<? extends SagaStep<?>>, Object> data = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void put(Class<? extends SagaStep<T>> stepClass, Object value) {
        data.put(stepClass, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<? extends SagaStep<T>> stepClass) {
        return (T) data.get(stepClass);
    }
}
