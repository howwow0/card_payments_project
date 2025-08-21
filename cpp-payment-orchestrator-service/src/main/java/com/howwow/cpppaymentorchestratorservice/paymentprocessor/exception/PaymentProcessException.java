package com.howwow.cpppaymentorchestratorservice.paymentprocessor.exception;

import com.howwow.cpppaymentorchestratorservice.rest.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class PaymentProcessException extends AbstractApiException {
    public PaymentProcessException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }
}
