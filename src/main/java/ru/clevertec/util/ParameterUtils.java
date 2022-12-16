package ru.clevertec.util;

import ru.clevertec.model.Product;

import java.util.List;

/**
 * Класс, содержащий статические методы для работы с входными параметрами.
 *
 * @author Ловцов Алексей
 */
public class ParameterUtils {

    /**
     * Проверка входного массива строк на null/
     *
     * @param args массив строк
     * @return the boolean
     */
    public static boolean checkForNull(String[] args) {
        return args == null || args.length == 0;
    }

    /**
     * Список продуктов содержит идентификатор.
     *
     * @param list список продуктов
     * @param id   идентификатор
     * @return the boolean
     */
    public static boolean containsId(List<Product> list, Integer id) {
        return list.stream().anyMatch(o -> o.getId().equals(id));
    }
}
