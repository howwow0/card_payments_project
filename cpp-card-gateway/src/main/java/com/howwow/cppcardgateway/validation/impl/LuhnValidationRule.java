package com.howwow.cppcardgateway.validation.impl;

import com.howwow.cppcardgateway.validation.ValidationRule;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class LuhnValidationRule implements ValidationRule<String> {
    private static final Pattern CARD_PATTERN = Pattern.compile("^[0-9]{13,19}$");

    @Override
    public boolean isValid(String s) {
        if (s == null || s.isBlank()) {
            return false;
        }

        if (!CARD_PATTERN.matcher(s).matches()) {
            return false;
        }

        return luhnCheck(s);
    }

    private boolean luhnCheck(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alternate) {
                digit *= 2;
                sum += digit > 9 ? (digit % 10) + 1 : digit;
            } else {
                sum += digit;
            }
            alternate = !alternate;
        }

        return (sum % 10) == 0;
    }
}
