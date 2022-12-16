package ru.clevertec.dao;

import ru.clevertec.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Dao интерфейс для продукта.
 *
 * @author Ловцов Алексей
 */
public interface ProductDao {

    /**
     * Найти продукт по id.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Product> findById(Integer id);

    /**
     * Найти все продукты.
     *
     * @param size the size
     * @param page the page
     * @return the list
     */
    List<Product> findAll(Integer size, Integer page);

    /**
     * Добавить продукт.
     *
     * @param product the product
     * @return the product
     */
    Product add(Product product);

    /**
     * Обновить продукт.
     *
     * @param product the product
     * @param id      the id
     * @return the optional
     */
    Optional<Product> update(Product product, Integer id);

    /**
     * Удалить продукт.
     *
     * @param id the id
     */
    void delete(Integer id);
}
