package com.howwow.cppcardvalidator.validation.validator;

import com.howwow.cppcardvalidator.validation.annotation.ValidExpiryDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ExpiryDateValidator implements ConstraintValidator<ValidExpiryDate, String> {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MM/yy");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        try {
            YearMonth expiryDate = YearMonth.parse(value, FORMATTER);
            YearMonth currentDate = YearMonth.now();

            return !expiryDate.isBefore(currentDate);

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
