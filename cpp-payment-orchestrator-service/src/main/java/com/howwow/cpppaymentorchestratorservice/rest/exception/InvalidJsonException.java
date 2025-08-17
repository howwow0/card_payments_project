package com.howwow.cpppaymentorchestratorservice.rest.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(description = "Ошибка обработки входного JSON")
public class InvalidJsonException extends AbstractApiException {
    public InvalidJsonException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
