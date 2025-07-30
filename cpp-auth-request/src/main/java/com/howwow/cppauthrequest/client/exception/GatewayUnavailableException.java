package com.howwow.cppauthrequest.client.exception;

public class GatewayUnavailableException extends RuntimeException {
    public GatewayUnavailableException() {
        super("Шлюз временно недоступен");
    }
}
