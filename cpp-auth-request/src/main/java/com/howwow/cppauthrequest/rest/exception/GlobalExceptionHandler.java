package com.howwow.cppauthrequest.rest.exception;

import com.howwow.cppauthrequest.client.exception.GatewayUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GatewayUnavailableException.class)
    public ResponseEntity<String> handleGatewayUnavailable(GatewayUnavailableException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("GATEWAY_UNAVAILABLE");
    }

}
