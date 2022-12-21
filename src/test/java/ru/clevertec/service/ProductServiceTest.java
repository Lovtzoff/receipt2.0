package ru.clevertec.service;

import org.junit.jupiter.api.*;
import ru.clevertec.dao.impl.ProductDaoImpl;
import ru.clevertec.exception.InputDataException;
import ru.clevertec.model.Product;
import ru.clevertec.service.impl.ProductServiceImpl;
import ru.clevertec.util.test.ProductTestUtils;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Тесты сервиса для продуктов.
 *
 * @author Ловцов Алексей
 */
class ProductServiceTest {

    /**
     * Тестовый список продуктов.
     */
    static List<Product> productList;
    /**
     * Сервис для продуктов.
     */
    ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

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
    void findOneByIdTest() {
        Product product = new Product(15, "Миксер(Мешалка) для смешивания смесей 100*500", 84.0);
        Assertions.assertEquals(product, productService.findOneById(15));
    }

    /**
     * Тест поиска продукта по id с ошибкой.
     */
    @Test
    void findOneByIdFailedTest() {
        Assertions.assertThrows(InputDataException.class, () -> productService.findOneById(50));
    }

    /**
     * Тест поиска всех продуктов.
     */
    @Test
    void findAllTest() {
        String pageSize = "30";
        List<Product> products = productService.findAll(pageSize, null);
        Assertions.assertEquals(productList.size(), products.size());
        IntStream.range(0, Integer.parseInt(pageSize))
                .forEach(i -> Assertions.assertEquals(productList.get(i), products.get(i)));
    }

    /**
     * Тест добавления, обновления и удаления продукта.
     */
    @Test
    void testSaveUpdateRemove() {
        // test save
        Product product = new Product();
        product.setName("Вагонка СЛ (Осина) СОРТ \"АВ\" 16х94(85)х2000мм (8шт.)");
        product.setPrice(26.84);
        productService.save(product);
        int productId = product.getId();
        Assertions.assertEquals(product, productService.findOneById(productId));

        // test update
        product.setName("Брусок профилированный обрезной сухой береза 15х40х2000 мм");
        product.setPrice(1.94);
        productService.update(product, productId);
        Assertions.assertEquals(product, productService.findOneById(productId));

        // test remove
        productService.remove(productId);
        Assertions.assertThrows(InputDataException.class, () -> productService.findOneById(productId));
    }
}