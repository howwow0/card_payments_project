package com.howwow.cppcardgateway.validation.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CvvValidationRuleTest {

    private final CvvValidationRule rule = new CvvValidationRule();

    @ParameterizedTest
    @CsvSource({
            "123, true",
            "1234, true",
            "12, false",
            "12345, false",
            "'', false",
            "'   ', false",
            "abc, false",
            "12a, false",
            "000, true"
    })
    void testIsValid(String input, boolean expected) {
        assertEquals(expected, rule.isValid(input));
    }
}
