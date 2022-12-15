package ru.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс заголовка чека.
 *
 * @author Ловцов Алексей
 */
@Data
@AllArgsConstructor
public class Header {

    /**
     * Название магазина.
     */
    private String shopName;
    /**
     * Город.
     */
    private String city;
    /**
     * Улица.
     */
    private String street;
    /**
     * Телевон.
     */
    private String telephone;

    @Override
    public String toString() {
        return shopName + "\n" +
                city + "\n" +
                street + "\n" +
                "Tel.: " + telephone;
    }
}
