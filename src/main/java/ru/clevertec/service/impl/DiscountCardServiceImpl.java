package ru.clevertec.service.impl;

import ru.clevertec.dao.DiscountCardDao;
import ru.clevertec.exception.InputDataException;
import ru.clevertec.model.DiscountCard;
import ru.clevertec.service.DiscountCardService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.clevertec.constants.Constants.DEFAULT_PAGE;
import static ru.clevertec.constants.Constants.DEFAULT_SIZE_PAGE;

/**
 * Реализация интерфейса DiscountCardService.
 *
 * @author Ловцов Алексей
 * @see DiscountCardService
 */
public class DiscountCardServiceImpl implements DiscountCardService {

    /**
     * Получение данных из базы.
     */
    private final DiscountCardDao discountCardDao;

    /**
     * Конструктор нового сервиса для дисконтной карты, в который передаются данные из базы.
     *
     * @param discountCardDao считыватель данных
     * @see DiscountCardServiceImpl#DiscountCardServiceImpl(DiscountCardDao)
     */
    public DiscountCardServiceImpl(DiscountCardDao discountCardDao) {
        this.discountCardDao = discountCardDao;
    }

    //------------------------------------------------------------------------------------------------

    @Override
    public DiscountCard findOneById(Integer id) {
        return discountCardDao.findById(id).orElse(new DiscountCard());
    }

    @Override
    public List<DiscountCard> findAll(String size, String page) {
        int pageSize = (size != null) ? Integer.parseInt(size) : DEFAULT_SIZE_PAGE;
        int pageNumber = (page != null) ? (Integer.parseInt(page) * pageSize) : DEFAULT_PAGE;

        try {
            List<DiscountCard> discountCards = discountCardDao.findAll(pageSize, pageNumber);
            if (!discountCards.isEmpty()) {
                return discountCards;
            }
            throw new InputDataException("Ошибка чтения списка дисконтных карт! База карт пуста!");
        } catch (InputDataException ex) {
            System.out.println(ex.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public DiscountCard save(DiscountCard discountCard) {
        return discountCardDao.add(discountCard);
    }

    @Override
    public DiscountCard update(DiscountCard discountCard, Integer id) {
        Optional<DiscountCard> optionalProduct = discountCardDao.findById(id);
        if (optionalProduct.isPresent()) {
            return discountCardDao.update(discountCard, id).get();
        }
        throw new InputDataException("Карта отсутствует в базе!");
    }

    @Override
    public void remove(Integer id) {
        discountCardDao.delete(id);
    }
}
