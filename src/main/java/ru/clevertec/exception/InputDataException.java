package ru.clevertec.exception;

/**
 * Сигналы о том, что произошло какое-то исключение, связанное с входными данными.
 *
 * @author Ловцов Алексей
 */
public class InputDataException extends RuntimeException {

    /**
     * Создает новое исключение "Input Data Exception", с сообщением по умолчанию.
     */
    public InputDataException() {
        super("Параметры не заданы.");
    }

    /**
     * Создает новое исключение "Input Data Exception", с заданным сообщением.
     *
     * @param message сообщение
     */
    public InputDataException(String message) {
        super(message);
    }
}
