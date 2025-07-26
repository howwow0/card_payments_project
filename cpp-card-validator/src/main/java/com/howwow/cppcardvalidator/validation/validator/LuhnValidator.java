package com.howwow.cppcardvalidator.validation.validator;

import com.howwow.cppcardvalidator.validation.annotation.ValidLuhn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LuhnValidator implements ConstraintValidator<ValidLuhn, String> {

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
        if (cardNumber == null || cardNumber.isBlank()) {
            return false;
        }

        if (!cardNumber.matches("^[0-9]{13,19}$")) {
            return false;
        }

        return luhnCheck(cardNumber);
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