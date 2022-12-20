package ru.clevertec.controller.servlets.discountcard;

import lombok.SneakyThrows;
import ru.clevertec.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.service.DiscountCardService;
import ru.clevertec.service.impl.DiscountCardServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet удалить дисконтную карту.
 *
 * @author Ловцов Алексей
 */
@WebServlet("/api/card/remove")
public class RemoveCardServlet extends HttpServlet {

    /**
     * Сервис дисконтной карты.
     */
    private final DiscountCardService discountCardService = new DiscountCardServiceImpl(new DiscountCardDaoImpl());

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("id"));
        discountCardService.remove(id);
    }
}
