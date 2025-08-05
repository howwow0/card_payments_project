package com.howwow.cppcardgateway.validation.impl;

import com.howwow.cppcardgateway.validation.ValidationRule;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class ExpiryDateValidationRule implements ValidationRule<String> {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MM/yy");

    @Override
    public boolean isValid(String s) {
        if (s == null || s.isBlank()) {
            return false;
        }

        try {
            YearMonth expiryDate = YearMonth.parse(s, FORMATTER);
            YearMonth currentDate = YearMonth.now();

            return !expiryDate.isBefore(currentDate);

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
