package ru.clevertec.controller.servlets.product;

import lombok.SneakyThrows;
import ru.clevertec.dao.impl.ProductDaoImpl;
import ru.clevertec.service.ProductService;
import ru.clevertec.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet удалить продукт.
 *
 * @author Ловцов Алексей
 */
@WebServlet("/api/product/remove")
public class RemoveProductServlet extends HttpServlet {

    /**
     * Сервис продукта.
     */
    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("id"));
        productService.remove(id);
    }
}
