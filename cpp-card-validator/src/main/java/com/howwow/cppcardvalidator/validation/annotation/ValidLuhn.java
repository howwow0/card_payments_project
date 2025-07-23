package com.howwow.cppcardvalidator.validation.annotation;

import com.howwow.cppcardvalidator.validation.validator.LuhnValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LuhnValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidLuhn {
    String message() default "Неверный номер карты (Не удалось проверить номер карты)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
