package ru.clevertec.model.parent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Абстрактный класс BaseModel с полем <b>id</b>.
 *
 * @author Ловцов Алексей
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseModel {

    /**
     * Идентификатор.
     */
    private Integer id;
}
