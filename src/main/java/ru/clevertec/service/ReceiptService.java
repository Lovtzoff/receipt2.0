package ru.clevertec.service;

import ru.clevertec.model.Receipt;

/**
 * Интерфейс с сервисом для магазинного чека.
 *
 * @author Ловцов Алексей
 */
public interface ReceiptService {

    /**
     * Сгенерировать чек.
     *
     * @param args входные параметры программы
     * @return чек
     */
    Receipt generateReceipt(String[] args);

    /**
     * Вывести чек в консоль и в файл.
     *
     * @param receipt чек
     */
    void printReceipt(Receipt receipt);
}
