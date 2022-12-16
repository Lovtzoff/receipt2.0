package ru.clevertec.service.impl;

import ru.clevertec.dao.ProductDao;
import ru.clevertec.exception.InputDataException;
import ru.clevertec.model.Product;
import ru.clevertec.service.ProductService;

import java.util.List;
import java.util.Optional;

import static ru.clevertec.constants.Constants.DEFAULT_PAGE;
import static ru.clevertec.constants.Constants.DEFAULT_SIZE_PAGE;

/**
 * Реализация интерфейса ProductService.
 *
 * @author Ловцов Алексей
 * @see ProductService
 */
public class ProductServiceImpl implements ProductService {

    /**
     * Получение данных из базы.
     */
    private final ProductDao productDao;

    /**
     * Конструктор нового сервиса для продукта, в который передаются данные из базы.
     *
     * @param productDao считыватель данных
     * @see ProductServiceImpl#ProductServiceImpl(ProductDao)
     */
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    //------------------------------------------------------------------------------------------------

    @Override
    public Product findOneById(Integer id) {
        Optional<Product> optionalProduct = productDao.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new InputDataException("Присутствуют продукты, отсутствующие в каталоге!");
    }

    @Override
    public List<Product> findAll(String size, String page) {
        int pageSize = (size != null) ? Integer.parseInt(size) : DEFAULT_SIZE_PAGE;
        int pageNumber = (page != null) ? (Integer.parseInt(page) * pageSize) : DEFAULT_PAGE;

        List<Product> products = productDao.findAll(pageSize, pageNumber);
        if (!products.isEmpty()) {
            return products;
        }
        throw new InputDataException("Ошибка чтения каталога продуктов! База товаров пуста!");
    }

    @Override
    public Product save(Product product) {
        return productDao.add(product);
    }

    @Override
    public Product update(Product product, Integer id) {
        Optional<Product> optionalProduct = productDao.findById(id);
        if (optionalProduct.isPresent()) {
            return productDao.update(product, id).get();
        }
        throw new InputDataException("Продукт отсутствует в базе!");
    }

    @Override
    public void remove(Integer id) {
        productDao.delete(id);
    }
}
