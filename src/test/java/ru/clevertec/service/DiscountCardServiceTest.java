package ru.clevertec.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.clevertec.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.model.DiscountCard;
import ru.clevertec.service.impl.DiscountCardServiceImpl;
import ru.clevertec.util.test.DiscountCardTestUtils;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Тесты сервиса для дисконтных карт.
 *
 * @author Ловцов Алексей
 */
class DiscountCardServiceTest {

    /**
     * Тестовый список дисконтных карт.
     */
    static List<DiscountCard> discountCardList;
    /**
     * Сервис для дисконтных карт.
     */
    DiscountCardService discountCardService = new DiscountCardServiceImpl(new DiscountCardDaoImpl());

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
    void findOneByIdTest() {
        DiscountCard discountCard = new DiscountCard(17, 5);
        Assertions.assertEquals(discountCard, discountCardService.findOneById(17));
    }

    /**
     * Тесты на ноль для поиска карты по id.
     */
    @Test
    void findOneByIdTestForNull() {
        Assertions.assertEquals(0, discountCardService.findOneById(1136).getId());
        Assertions.assertEquals(0, discountCardService.findOneById(1136).getDiscount());
    }

    static IntStream generateMissingIds() {
        return IntStream.range(31, 33);
    }

    @ParameterizedTest
    @MethodSource("generateMissingIds")
    void findOneByIdTestForNull1(Integer missingId) {
        Assertions.assertEquals(0, discountCardService.findOneById(missingId).getId());
        Assertions.assertEquals(0, discountCardService.findOneById(missingId).getDiscount());
    }

    /**
     * Тест поиска всех карт.
     */
    @Test
    void findAllTest() {
        String pageSize = "30";
        List<DiscountCard> cards = discountCardService.findAll(pageSize, null);
        Assertions.assertEquals(discountCardList.size(), cards.size());
        IntStream.range(0, Integer.parseInt(pageSize))
                .forEach(i -> Assertions.assertEquals(discountCardList.get(i), cards.get(i)));
    }

    /**
     * Тест добавления карты.
     */
    @Test
    @Disabled
    void save() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setDiscount(15);
        discountCardService.save(discountCard);
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
        discountCardService.update(discountCard, 32);
        System.out.println(discountCard);
    }

    /**
     * Тест удаления карты.
     */
    @Test
    @Disabled
    void remove() {
        discountCardService.remove(32);
    }
}