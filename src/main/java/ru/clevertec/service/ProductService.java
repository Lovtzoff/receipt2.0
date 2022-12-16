package ru.clevertec.service;

import ru.clevertec.model.Product;

import java.util.List;

/**
 * Интерфейс с сервисом для продуктов.
 *
 * @author Ловцов Алексей
 */
public interface ProductService {

    /**
     * Найти продукт по id.
     *
     * @param id the id
     * @return the product
     */
    Product findOneById(Integer id);

    /**
     * Найти список всех продуктов.
     *
     * @param size the size
     * @param page the page
     * @return the list
     */
    List<Product> findAll(String size, String page);

    /**
     * Сохранить продукт.
     *
     * @param product the product
     * @return the product
     */
    Product save(Product product);

    /**
     * Обновить продукт.
     *
     * @param product the product
     * @param id      the id
     * @return the product
     */
    Product update(Product product, Integer id);

    /**
     * Удалить продукт.
     *
     * @param id the id
     */
    void remove(Integer id);
}
