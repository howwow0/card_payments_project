package com.howwow.cppcardgateway.validation.impl;

import com.howwow.cppcardgateway.validation.ValidationRule;
import org.springframework.stereotype.Component;

@Component
public class CvvValidationRule implements ValidationRule<String> {
    @Override
    public boolean isValid(String s) {
        return s != null && !s.isBlank() && s.matches("\\d{3,4}");
    }
}

