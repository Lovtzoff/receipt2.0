package ru.clevertec.util;

/**
 * Класс, содержащий статические методы для работы со строками.
 *
 * @author Ловцов Алексей
 */
public class StringUtils {

    /**
     * Является ли строка пустой.
     * <ul>
     * <li> если строка пуста, null или содержит только пробелы, то вернет true
     * <li> если строка содержит любой символ кроме пробела, вернет false
     * </ul>
     *
     * @param source входная строка
     * @return логическое значение
     */
    public static boolean isEmpty(CharSequence source) {
        int sourceLength;
        if (source != null && (sourceLength = source.length()) != 0) {
            for (int symbol = 0; symbol < sourceLength; ++symbol) {
                if (!Character.isWhitespace(source.charAt(symbol))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Является ли строка не пустой.
     *
     * @param source входная строка
     * @return логическое значение
     */
    public static boolean isNotEmpty(CharSequence source) {
        return !isEmpty(source);
    }
}
