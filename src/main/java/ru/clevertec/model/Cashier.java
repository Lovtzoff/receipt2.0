package ru.clevertec.model;

import lombok.EqualsAndHashCode;
import ru.clevertec.model.parent.BaseModel;

/**
 * Класс модели кассира с полем <b>id</b>, унаследованным от абстрактного класса BaseModel.
 *
 * @author Ловцов Алексей
 * @see BaseModel
 */
@EqualsAndHashCode(callSuper = false)
public class Cashier extends BaseModel {

    /**
     * Конструктор нового кассира.
     *
     * @param id идентификатор
     * @see Cashier#Cashier(Integer)
     */
    public Cashier(Integer id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Cashier №" + super.getId();
    }
}
