package com.howwow.cppauthrequest.rest.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Schema(description = "Ошибка валидации входных данных")
public class ValidationErrorException extends AbstractApiException {

    @Schema(description = "Список нарушений валидации")
    private final List<Violation> violations;

    public ValidationErrorException(List<Violation> violations) {
        super(HttpStatus.BAD_REQUEST, "Произошла ошибка валидации");
        this.violations = violations;
    }

    @Schema(description = "Описание одного нарушения валидации")
    public record Violation(String fieldName, String message) {
    }
}
