package ru.clevertec;

import ru.clevertec.exception.InputDataException;
import ru.clevertec.model.Receipt;
import ru.clevertec.service.ReceiptService;
import ru.clevertec.service.impl.ReceiptServiceImpl;
import ru.clevertec.validation.ParameterValidator;

/**
 * Основной класс приложения.
 */
public class ReceiptRunner {

    /**
     * Точка запуска приложения.
     *
     * @param args массив входных параметров
     */
    public static void main(String[] args) {
        try {
            ParameterValidator.isValid(args);
            ReceiptService receiptService = new ReceiptServiceImpl();
            Receipt receipt = receiptService.generateReceipt(args);
            receiptService.printReceipt(receipt);
        } catch (InputDataException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
    }
}