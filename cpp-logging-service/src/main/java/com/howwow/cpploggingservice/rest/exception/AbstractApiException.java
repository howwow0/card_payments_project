package com.howwow.cpploggingservice.rest.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage"})
@Schema(description = "Общий формат API-ошибки")
public abstract class AbstractApiException extends RuntimeException {

    @Schema(description = "HTTP статус", example = "400")
    protected final HttpStatus httpStatus;

    @Schema(description = "Сообщение об ошибке", example = "Validation error")
    protected final String message;

    public AbstractApiException() {
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        message = "Сбой в работе сервера";
    }

    public ResponseEntity<AbstractApiException> asResponse() {
        return ResponseEntity.status(httpStatus).body(this);
    }
}
