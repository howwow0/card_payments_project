package com.howwow.cppbankgateway.rest.exception;

import com.howwow.cppbankgateway.rest.dto.response.GatewayAuthorizationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GatewayAuthorizationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringJoiner joiner = new StringJoiner(", ");
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            joiner.add(error.getDefaultMessage());
        }
        return ResponseEntity.ok(GatewayAuthorizationResponse.rejected(UUID.randomUUID(), joiner.toString()));
    }
}
