package ru.clevertec.controller.servlets.discountcard;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import ru.clevertec.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.model.DiscountCard;
import ru.clevertec.service.DiscountCardService;
import ru.clevertec.service.impl.DiscountCardServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet добавить дисконтную карту.
 *
 * @author Ловцов Алексей
 */
@WebServlet("/api/card/add")
public class AddCardServlet extends HttpServlet {

    /**
     * Сервис дисконтной карты.
     */
    private final DiscountCardService discountCardService = new DiscountCardServiceImpl(new DiscountCardDaoImpl());

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Integer discount = Integer.valueOf(req.getParameter("discount"));
        DiscountCard discountCard = new DiscountCard(discount);
        discountCardService.save(discountCard);
        String json = new Gson().toJson(discountCard);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(json);
            resp.setContentType("application/json");
            resp.setStatus(201);
        }
    }
}
