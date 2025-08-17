package com.howwow.cpppaymentgatewayservice.validation.impl;

import com.howwow.cpppaymentgatewayservice.validation.ValidationRule;
import org.springframework.stereotype.Component;

@Component
public class CvvValidationRule implements ValidationRule<String> {
    @Override
    public boolean isValid(String s) {
        return s != null && !s.isBlank() && s.matches("\\d{3,4}");
    }
}

