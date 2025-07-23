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

        String digitsOnly = cardNumber.replaceAll("[^0-9]", "");

        if (digitsOnly.length() < 13 || digitsOnly.length() > 19) {
            return false;
        }

        return luhnCheck(digitsOnly);
    }

    private boolean luhnCheck(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return (sum % 10) == 0;
    }
}