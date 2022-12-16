package ru.clevertec.service;

import ru.clevertec.model.DiscountCard;

import java.util.List;

/**
 * Интерфейс с сервисом для дисконтной карты.
 *
 * @author Ловцов Алексей
 */
public interface DiscountCardService {

    /**
     * Найти дисконтную карту по id.
     *
     * @param id the id
     * @return the discount card
     */
    DiscountCard findOneById(Integer id);

    /**
     * Найти список всех дисконтных карт.
     *
     * @param size the size
     * @param page the page
     * @return the list
     */
    List<DiscountCard> findAll(String size, String page);

    /**
     * Сохранить карту.
     *
     * @param discountCard the discount card
     * @return the discount card
     */
    DiscountCard save(DiscountCard discountCard);

    /**
     * Обновить карту.
     *
     * @param discountCard the discount card
     * @param id           the id
     * @return the discount card
     */
    DiscountCard update(DiscountCard discountCard, Integer id);

    /**
     * Удалить карту.
     *
     * @param id the id
     */
    void remove(Integer id);
}
