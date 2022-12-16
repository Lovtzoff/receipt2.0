package ru.clevertec.dao;

import org.junit.jupiter.api.*;
import ru.clevertec.dao.impl.ProductDaoImpl;
import ru.clevertec.model.Product;
import ru.clevertec.util.test.ProductTestUtils;

import java.util.List;
import java.util.stream.IntStream;

import static ru.clevertec.constants.Constants.DEFAULT_PAGE;

/**
 * Тесты dao для продуктов.
 *
 * @author Ловцов Алексей
 */
class ProductDaoTest {

    private final ProductDao productDao = new ProductDaoImpl();
    /**
     * Тестовый список продуктов.
     */
    static List<Product> productList;

    /**
     * Сгенерировать список продуктов.
     */
    @BeforeAll
    static void generateProducts() {
        productList = ProductTestUtils.createProductList();
    }

    /**
     * Удалить список продуктов.
     */
    @AfterAll
    static void deleteProducts() {
        productList = null;
    }

    /**
     * Тест поиска продукта по id.
     */
    @Test
    void findById() {
        Product product = new Product(15, "Миксер(Мешалка) для смешивания смесей 100*500", 84.0);
        Assertions.assertEquals(product, productDao.findById(15).get());
    }

    /**
     * Тест поиска всех продуктов.
     */
    @Test
    void findAll() {
        int pageSize = 30;
        List<Product> products = productDao.findAll(pageSize, DEFAULT_PAGE);
        Assertions.assertEquals(productList.size(), products.size());
        IntStream.range(0, pageSize)
                .forEach(i -> Assertions.assertEquals(productList.get(i), products.get(i)));
    }

    /**
     * Тест добавления продукта.
     */
    @Test
    @Disabled
    void add() {
        Product product = new Product();
        product.setName("Вагонка СЛ (Осина) СОРТ \"АВ\" 16х94(85)х2000мм (8шт.)");
        product.setPrice(26.84);
        productDao.add(product);
        System.out.println(product);
    }

    /**
     * Тест обновления продукта.
     */
    @Test
    @Disabled
    void update() {
        Product product = new Product();
        product.setName("Брусок профилированный обрезной сухой береза 15х40х2000 мм");
        product.setPrice(1.94);
        productDao.update(product, 31);
        System.out.println(product);
    }

    /**
     * Тест удаления продукта.
     */
    @Test
    @Disabled
    void delete() {
        productDao.delete(31);
    }
}