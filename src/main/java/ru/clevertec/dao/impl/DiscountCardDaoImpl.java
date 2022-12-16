package ru.clevertec.dao.impl;

import org.intellij.lang.annotations.Language;
import ru.clevertec.dao.DiscountCardDao;
import ru.clevertec.model.DiscountCard;
import ru.clevertec.util.database.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация интерфейса DiscountCardDao.
 *
 * @author Ловцов Алексей
 * @see DiscountCardDao
 */
public class DiscountCardDaoImpl implements DiscountCardDao {

    @Language("SQL")
    private static final String FIND_BY_ID = "SELECT * FROM discount_card WHERE id = ?";
    @Language("SQL")
    private static final String FIND_ALL = "SELECT * FROM discount_card LIMIT (?) OFFSET (?)";
    @Language("SQL")
    private static final String ADD_DISCOUNT_CARD = "INSERT INTO discount_card (discount) VALUES (?)";
    @Language("SQL")
    private static final String UPDATE_DISCOUNT_CARD = "UPDATE discount_card SET discount = ? WHERE id = ?";
    @Language("SQL")
    private static final String DELETE_DISCOUNT_CARD_BY_ID = "DELETE FROM discount_card WHERE id = ?";

    private final Connection connection = DBConnectionPool.INSTANCE.getConnection();

    @Override
    public Optional<DiscountCard> findById(Integer id) {
        Optional<DiscountCard> optionalDiscountCard = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DiscountCard discountCard = new DiscountCard();
                discountCard.setId(resultSet.getInt("id"));
                discountCard.setDiscount(resultSet.getInt("discount"));
                optionalDiscountCard = Optional.of(discountCard);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalDiscountCard;
    }

    @Override
    public List<DiscountCard> findAll(Integer size, Integer page) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            statement.setInt(1, size);
            statement.setInt(2, page);
            ResultSet resultSet = statement.executeQuery();
            List<DiscountCard> discountCards = new ArrayList<>(resultSet.getFetchSize());
            while (resultSet.next()) {
                DiscountCard discountCard = new DiscountCard();
                discountCard.setId(resultSet.getInt("id"));
                discountCard.setDiscount(resultSet.getInt("discount"));
                discountCards.add(discountCard);
            }
            return discountCards;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DiscountCard add(DiscountCard discountCard) {
        try (PreparedStatement statement = connection.prepareStatement(
                ADD_DISCOUNT_CARD,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, discountCard.getDiscount());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                final int key = generatedKeys.getInt(1);
                discountCard.setId(key);
            }
            return discountCard;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DiscountCard> update(DiscountCard discountCard, Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_DISCOUNT_CARD)) {
            statement.setInt(1, discountCard.getDiscount());
            statement.setInt(2, id);
            statement.executeUpdate();
            discountCard.setId(id);
            return Optional.of(discountCard);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_DISCOUNT_CARD_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
