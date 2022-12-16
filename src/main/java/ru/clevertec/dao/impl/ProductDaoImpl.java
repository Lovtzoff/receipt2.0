package ru.clevertec.dao.impl;

import org.intellij.lang.annotations.Language;
import ru.clevertec.dao.ProductDao;
import ru.clevertec.model.Product;
import ru.clevertec.util.database.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация интерфейса ProductDao.
 *
 * @author Ловцов Алексей
 * @see ProductDao
 */
public class ProductDaoImpl implements ProductDao {

    @Language("SQL")
    private static final String FIND_BY_ID = "SELECT * FROM product WHERE id = ?";
    @Language("SQL")
    private static final String FIND_ALL = "SELECT * FROM product LIMIT (?) OFFSET (?)";
    @Language("SQL")
    private static final String ADD_PRODUCT = "INSERT INTO product (productName, price) VALUES (?, ?)";
    @Language("SQL")
    private static final String UPDATE_PRODUCT = "UPDATE product SET productName = ?, price = ? WHERE id = ?";
    @Language("SQL")
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id = ?";

    private final Connection connection = DBConnectionPool.INSTANCE.getConnection();

    @Override
    public Optional<Product> findById(Integer id) {
        Optional<Product> optionalProduct = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("productName"));
                product.setPrice(resultSet.getDouble("price"));
                optionalProduct = Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalProduct;
    }

    @Override
    public List<Product> findAll(Integer size, Integer page) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            statement.setInt(1, size);
            statement.setInt(2, page);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>(resultSet.getFetchSize());
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("productName"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product add(Product product) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                final int key = generatedKeys.getInt(1);
                product.setId(key);
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> update(Product product, Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, id);
            statement.executeUpdate();
            product.setId(id);
            return Optional.of(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
