package com.howwow.cpppaymentorchestratorservice.bankgateway.exception;

import com.howwow.cpppaymentorchestratorservice.rest.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class PaymentAuthorizationException extends AbstractApiException {
    public PaymentAuthorizationException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }
}
