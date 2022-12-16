package ru.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.clevertec.model.parent.BaseModel;

/**
 * Класс товара с полем <b>id</b>, унаследованным от абстрактного класса BaseModel,
 * и полями <b>name</b>, и <b>price</b>.
 *
 * @author Ловцов Алексей
 * @see BaseModel
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseModel {

    /**
     * Название.
     */
    private String name;
    /**
     * Цена за единицу товара.
     */
    private Double price;

    /**
     * Конструктор нового товара.
     *
     * @param id    идентификатор
     * @param name  название
     * @param price цена
     * @see Product#Product(Integer, String, Double)
     */
    public Product(Integer id, String name, Double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "id = " + super.getId() +
                ", name = " + name +
                ", price = $" + price;
    }
}
