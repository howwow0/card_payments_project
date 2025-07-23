package com.howwow.cppcardvalidator.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;

@Pattern(regexp = "\\d{3,4}", message = "CVV код должен содержать только цифры")
@Size(min = 3, max = 4, message = "CVV код должен быть 3 или 4 цифры")
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCVV {
    String message() default "Неверный формат CVV кода";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}