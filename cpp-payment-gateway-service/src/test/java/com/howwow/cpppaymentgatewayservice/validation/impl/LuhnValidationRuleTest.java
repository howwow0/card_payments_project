package com.howwow.cpppaymentgatewayservice.validation.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LuhnValidationRuleTest {

    private final LuhnValidationRule rule = new LuhnValidationRule();

    @ParameterizedTest
    @CsvSource({
            // Валидные номера (с корректным Luhn)
            "4539578763621486, true",  // Visa
            "6011000990139424, true",  // Discover
            "378282246310005, true",   // AmEx
            // Неверные номера
            "4539578763621487, false",
            "1234567890123456, false",
            "1111111111111111, false",
            // Форматные ошибки
            "'', false",
            "'   ', false",
            "12345abc67890, false",
            "123, false"
    })
    void testIsValid(String input, boolean expected) {
        assertEquals(expected, rule.isValid(input));
    }
}
