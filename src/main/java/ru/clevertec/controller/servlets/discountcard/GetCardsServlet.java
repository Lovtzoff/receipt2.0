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
import java.util.List;

/**
 * Servlet получить дисконтные карты.
 *
 * @author Ловцов Алексей
 */
@WebServlet("/api/cards")
public class GetCardsServlet extends HttpServlet {

    /**
     * Сервис дисконтной карты.
     */
    private final DiscountCardService discountCardService = new DiscountCardServiceImpl(new DiscountCardDaoImpl());

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String size = req.getParameter("size");
        String page = req.getParameter("page");
        List<DiscountCard> discountCards = discountCardService.findAll(size, page);
        String json = new Gson().toJson(discountCards);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(json);
            resp.setContentType("application/json");
            resp.setStatus(200);
        }
    }
}
