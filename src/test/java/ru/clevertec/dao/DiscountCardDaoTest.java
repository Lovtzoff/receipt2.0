package ru.clevertec.dao;

import org.junit.jupiter.api.*;
import ru.clevertec.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.model.DiscountCard;
import ru.clevertec.util.test.DiscountCardTestUtils;

import java.util.List;
import java.util.stream.IntStream;

import static ru.clevertec.constants.Constants.DEFAULT_PAGE;

/**
 * Тесты dao для дисконтных карт.
 *
 * @author Ловцов Алексей
 */
class DiscountCardDaoTest {

    private final DiscountCardDao discountCardDao = new DiscountCardDaoImpl();
    /**
     * Тестовый список дисконтных карт.
     */
    static List<DiscountCard> discountCardList;

    /**
     * Сгенерировать список карт.
     */
    @BeforeAll
    static void generateDiscountCards() {
        discountCardList = DiscountCardTestUtils.createDiscountCardList();
    }

    /**
     * Удалить список карт.
     */
    @AfterAll
    static void deleteDiscountCards() {
        discountCardList = null;
    }

    /**
     * Тест поиска карты по id.
     */
    @Test
    void findById() {
        DiscountCard discountCard = new DiscountCard(10, 9);
        Assertions.assertEquals(discountCard, discountCardDao.findById(10).get());
    }

    /**
     * Тест поиска всех карт.
     */
    @Test
    void findAll() {
        int pageSize = 30;
        List<DiscountCard> cards = discountCardDao.findAll(pageSize, DEFAULT_PAGE);
        Assertions.assertEquals(discountCardList.size(), cards.size());
        IntStream.range(0, pageSize)
                .forEach(i -> Assertions.assertEquals(discountCardList.get(i), cards.get(i)));
    }

    /**
     * Тест добавления карты.
     */
    @Test
    @Disabled
    void add() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setDiscount(15);
        discountCardDao.add(discountCard);
        System.out.println(discountCard);
    }

    /**
     * Тест обновления карты.
     */
    @Test
    @Disabled
    void update() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setDiscount(20);
        discountCardDao.update(discountCard, 31);
        System.out.println(discountCard);
    }

    /**
     * Тест удаления карты.
     */
    @Test
    @Disabled
    void delete() {
        discountCardDao.delete(31);
    }
}