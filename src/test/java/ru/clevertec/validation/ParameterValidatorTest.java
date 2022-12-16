package ru.clevertec.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.clevertec.exception.InputDataException;

import java.util.stream.Stream;

import static ru.clevertec.constants.Constants.STRING_SEPARATOR;

/**
 * Проверка валидатора.
 *
 * @author Ловцов Алексей
 */
class ParameterValidatorTest {

    /**
     * Исходный массив.
     */
    static String[] sourceArray = new String[]{};

    /**
     * Тест проверки на действительность.
     */
    @Test
    void isValidTest() {
        Assertions.assertThrows(
                InputDataException.class,
                () -> ParameterValidator.isValid(sourceArray)
        );
    }

    /**
     * Генерация входных строк.
     *
     * @return the stream
     */
    static Stream<String> generateInputStrings() {
        return Stream.of(
                "-1 15-7 24-3 2-2 card-1207",
                "16- 20",
                "- 4- -9 card-1223"
        );
    }

    /**
     * Параметризованный тест проверки на действительность.
     *
     * @param inputStrings the input strings
     */
    @ParameterizedTest
    @MethodSource("generateInputStrings")
    void isValidParameterizedTest(String inputStrings) {
        Assertions.assertThrows(
                InputDataException.class,
                () -> ParameterValidator.isValid(inputStrings.split(STRING_SEPARATOR))
        );
    }
}