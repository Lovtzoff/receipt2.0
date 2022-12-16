package ru.clevertec.util;

/**
 * Класс, содержащий статические методы для работы с числами.
 *
 * @author Ловцов Алексей
 */
public class NumberUtils {

    /**
     * Является ли строка целым числом.
     *
     * @param source входная строка
     * @return логическое значение
     */
    public static boolean isNumeric(String source) {
        if (StringUtils.isEmpty(source)) {
            return false;
        }
        for (int i = 0; i < source.length(); i++) {
            if (!Character.isDigit(source.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
