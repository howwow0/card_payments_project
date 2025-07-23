package com.howwow.cppcardvalidator.validation.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LuhnValidatorTest {

    private final LuhnValidator validator = new LuhnValidator();

    static Stream<String> provideInvalidCardNumbers() {
        return Stream.of(
                null, "", "   ",
                "123456789012",             // меньше 13 цифр
                "12345678901234567890",     // больше 19 цифр
                "4111111111111110",         // неверная последняя цифра
                "abcdabcdabcdabcd"          // не цифры
        );
    }

    static Stream<String> provideValidCardNumbers() {
        return Stream.of(
                "4111111111111111",         // Visa
                "5500000000000004",         // MasterCard
                "340000000000009",          // American Express (15 цифр)
                "6011000000000004",         // Discover
                "4111 1111 1111 1111"       // с пробелами
        );
    }

    @ParameterizedTest(name = "Недействительные номер карты: «{0}»")
    @MethodSource("provideInvalidCardNumbers")
    void invalid_cardNumbers(String input) {
        assertFalse(validator.isValid(input, null),
                () -> "Ожидали false для входа: \"" + input + "\"");
    }

    @ParameterizedTest(name = "Действительный номер карты: «{0}»")
    @MethodSource("provideValidCardNumbers")
    void valid_cardNumbers(String input) {
        assertTrue(validator.isValid(input, null),
                () -> "Ожидали true для входа: \"" + input + "\"");
    }
}
