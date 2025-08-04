package com.howwow.cppsecurityservice.rest.exception;

import com.fasterxml.jackson.core.JsonParseException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@RestControllerAdvice
@Component
public class ExceptionResolver {


    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<?> handle(AbstractApiException cause, WebRequest request) {
        return cause.asResponse();
    }

    /**
     * Обработка исключений уровня {@link jakarta.validation.ConstraintViolationException},
     * возникающих при нарушении ограничений валидации на уровне параметров метода.
     *
     * @param cause   исключение с деталями нарушений
     * @param request объект запроса
     * @return структурированный ответ с информацией о валидационных ошибках
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handle(ConstraintViolationException cause, WebRequest request) {
        return new ValidationErrorException(
                cause.getConstraintViolations().stream()
                        .map(v -> new ValidationErrorException.Violation(
                                v.getPropertyPath().toString(),
                                v.getMessage())
                        )
                        .collect(Collectors.toList())
        ).asResponse();
    }

    /**
     * Обработка исключений {@link MethodArgumentNotValidException}, возникающих при валидации тела запроса.
     *
     * @param cause   исключение с ошибками привязки аргументов
     * @param request объект запроса
     * @return ответ с описанием всех нарушенных правил валидации
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException cause, WebRequest request) {
        return new ValidationErrorException(
                cause.getBindingResult().getFieldErrors().stream()
                        .map(v -> new ValidationErrorException.Violation(
                                v.getField(),
                                v.getDefaultMessage())
                        )
                        .collect(Collectors.toList())
        ).asResponse();
    }

    /**
     * Обработка исключений {@link JsonParseException}, возникающих при некорректном формате JSON-запроса.
     *
     * @param cause   ошибка разбора JSON
     * @param request объект запроса
     * @return ответ с сообщением об ошибке парсинга
     */
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<?> handle(JsonParseException cause, WebRequest request) {
        return new InvalidJsonException("Невозможно обработать JSON: " + cause.getOriginalMessage())
                .asResponse();
    }

}
