package com.howwow.cpppaymentrecordingservice.rest.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class PaymentNotFoundException extends AbstractApiException {
    public PaymentNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Платеж с transactionalID %s не найден".formatted(id));
    }
}
