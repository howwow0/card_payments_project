package com.howwow.cppcardvalidator.rest.exception;

import com.howwow.cppcardvalidator.rest.dto.response.CardValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CardValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringJoiner joiner = new StringJoiner(", ");
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            joiner.add(error.getDefaultMessage());
        }
        return ResponseEntity.ok(CardValidationResponse.failure(joiner.toString()));
    }
}
