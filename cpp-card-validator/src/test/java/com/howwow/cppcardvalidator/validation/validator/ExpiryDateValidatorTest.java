package com.howwow.cppcardvalidator.validation.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpiryDateValidatorTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yy");
    private final ExpiryDateValidator validator = new ExpiryDateValidator();

    static Stream<String> provideInvalidBlankOrBadFormats() {
        return Stream.of(null, "",
                "   ", "13/25", "00/20", "1/22", "12-25", "1225", "ab/cd");
    }

    static Stream<String> provideInvalidPastDates() {
        return Stream.of("01/20", "12/23");
    }

    static Stream<String> provideValidCurrentOrFutureDates() {
        String current = YearMonth.now().format(FORMATTER);
        String next = YearMonth.now().plusMonths(1).format(FORMATTER);
        return Stream.of(current, next);
    }

    @ParameterizedTest(name = "Недопустимо (пусто или неверный формат): «{0}»")
    @MethodSource("provideInvalidBlankOrBadFormats")
    void invalid_blankOrBadFormat(String input) {
        assertFalse(validator.isValid(input, null),
                () -> "Ожидали false для входа: \"" + input + "\"");
    }

    @ParameterizedTest(name = "Недопустимо – просрочено: «{0}»")
    @MethodSource("provideInvalidPastDates")
    void invalid_pastDates(String input) {
        assertFalse(validator.isValid(input, null),
                () -> "Ожидали false для прошедшей даты: \"" + input + "\"");
    }

    @ParameterizedTest(name = "Допустимо – текущая или будущая дата: «{0}»")
    @MethodSource("provideValidCurrentOrFutureDates")
    void valid_currentOrFuture(String input) {
        assertTrue(validator.isValid(input, null),
                () -> "Ожидали true для текущей/будущей даты: \"" + input + "\"");
    }
}
