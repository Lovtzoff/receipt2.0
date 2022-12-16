package ru.clevertec.util;

import ru.clevertec.constants.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс, содержащий статические методы для округления чисел.
 *
 * @author Ловцов Алексей
 */
public class RoundingUtils {

    /**
     * Округлить double.
     *
     * @param source входное число double
     * @return the double
     */
    public static double round(Double source) {
        BigDecimal result = new BigDecimal(source).setScale(Constants.SCALE_DEFAULT, RoundingMode.HALF_EVEN);
        return result.doubleValue();
    }
}
