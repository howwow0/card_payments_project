package com.howwow.cppcardvalidator.rest.exception;

import com.howwow.cppcardvalidator.rest.dto.response.CardValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CardValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String[] messages = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).toArray(String[]::new);
        String joiningMessage = String.join(", ", messages);
        return ResponseEntity.ok(CardValidationResponse.failure(joiningMessage));
    }
}
