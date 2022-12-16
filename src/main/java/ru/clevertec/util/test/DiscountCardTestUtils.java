package ru.clevertec.util.test;

import ru.clevertec.model.DiscountCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий утилиты для тестирования дисконтных карт.
 */
public class DiscountCardTestUtils {

    /**
     * Создать список дисконтных карт.
     *
     * @return список карт
     */
    public static List<DiscountCard> createDiscountCardList() {
        List<DiscountCard> discountCardList = new ArrayList<>(30);
        discountCardList.add(new DiscountCard(1, 3));
        discountCardList.add(new DiscountCard(2, 5));
        discountCardList.add(new DiscountCard(3, 1));
        discountCardList.add(new DiscountCard(4, 2));
        discountCardList.add(new DiscountCard(5, 6));
        discountCardList.add(new DiscountCard(6, 7));
        discountCardList.add(new DiscountCard(7, 8));
        discountCardList.add(new DiscountCard(8, 10));
        discountCardList.add(new DiscountCard(9, 4));
        discountCardList.add(new DiscountCard(10, 9));
        discountCardList.add(new DiscountCard(11, 1));
        discountCardList.add(new DiscountCard(12, 4));
        discountCardList.add(new DiscountCard(13, 3));
        discountCardList.add(new DiscountCard(14, 2));
        discountCardList.add(new DiscountCard(15, 7));
        discountCardList.add(new DiscountCard(16, 9));
        discountCardList.add(new DiscountCard(17, 5));
        discountCardList.add(new DiscountCard(18, 8));
        discountCardList.add(new DiscountCard(19, 10));
        discountCardList.add(new DiscountCard(20, 6));
        discountCardList.add(new DiscountCard(21, 7));
        discountCardList.add(new DiscountCard(22, 6));
        discountCardList.add(new DiscountCard(23, 4));
        discountCardList.add(new DiscountCard(24, 8));
        discountCardList.add(new DiscountCard(25, 10));
        discountCardList.add(new DiscountCard(26, 1));
        discountCardList.add(new DiscountCard(27, 2));
        discountCardList.add(new DiscountCard(28, 5));
        discountCardList.add(new DiscountCard(29, 9));
        discountCardList.add(new DiscountCard(30, 3));
        return discountCardList;
    }
}
