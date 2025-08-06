package com.howwow.cppauthrequest.rest.exception;

import com.howwow.cppauthrequest.client.exception.GatewayUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GatewayUnavailableException.class)
    public ResponseEntity<String> handleGatewayUnavailable(GatewayUnavailableException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("GATEWAY_UNAVAILABLE");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringJoiner joiner = new StringJoiner(", ");
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            joiner.add(error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(joiner.toString());
    }

}
