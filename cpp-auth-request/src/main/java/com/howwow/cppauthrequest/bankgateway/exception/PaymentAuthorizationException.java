package com.howwow.cppauthrequest.bankgateway.exception;

import lombok.Getter;

@Getter
public class PaymentAuthorizationException extends RuntimeException {

    private final String reason;

    public PaymentAuthorizationException(String reason) {
        super("Платеж не авторизован: " + reason);
        this.reason = reason;
    }
}
