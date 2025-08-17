package com.howwow.cpppaymentgatewayservice.validation.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpiryDateValidationRuleTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yy");
    private final ExpiryDateValidationRule rule = new ExpiryDateValidationRule();

    static Stream<Object[]> provideExpiryDates() {
        YearMonth now = YearMonth.now();
        YearMonth nextMonth = now.plusMonths(1);
        YearMonth lastMonth = now.minusMonths(1);

        return Stream.of(
                new Object[]{nextMonth.format(FORMATTER), true},
                new Object[]{now.format(FORMATTER), true},
                new Object[]{lastMonth.format(FORMATTER), false},
                new Object[]{"13/25", false},
                new Object[]{"00/25", false},
                new Object[]{"1/25", false},
                new Object[]{"0125", false},
                new Object[]{"", false},
                new Object[]{"   ", false},
                new Object[]{"abc", false}
        );
    }

    @ParameterizedTest
    @MethodSource("provideExpiryDates")
    void testIsValid(String input, boolean expected) {
        boolean actual = rule.isValid(input);
        assertEquals(expected, actual);
    }
}
