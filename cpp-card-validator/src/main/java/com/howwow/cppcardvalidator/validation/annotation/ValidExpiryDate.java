package com.howwow.cppcardvalidator.validation.annotation;

import com.howwow.cppcardvalidator.validation.validator.ExpiryDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpiryDateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidExpiryDate {
    String message() default "Недействительная или просроченная дата на карте (требуется формат ММ/ГГ)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
