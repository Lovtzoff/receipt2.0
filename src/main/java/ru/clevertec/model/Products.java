package ru.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс товара в чеке с полями <b>count</b>, <b>product</b> и <b>totalPrice</b>.
 *
 * @author Ловцов Алексей
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    /**
     * Количество товара в чеке.
     */
    private Integer count;
    /**
     * Товар.
     */
    private Product product;
    /**
     * Конечная цена товара, по количеству в чеке.
     */
    private Double totalPrice;

    @Override
    public String toString() {
        return "count = " + count +
                ", " + product +
                ", totalPrice = $" + totalPrice;
    }
}
