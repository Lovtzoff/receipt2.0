package ru.clevertec.controller.servlets.product;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import ru.clevertec.dao.impl.ProductDaoImpl;
import ru.clevertec.model.Product;
import ru.clevertec.service.ProductService;
import ru.clevertec.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet получить продукты.
 *
 * @author Ловцов Алексей
 */
@WebServlet("/api/products")
public class GetProductsServlet extends HttpServlet {

    /**
     * Сервис продукта.
     */
    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String size = req.getParameter("size");
        String page = req.getParameter("page");
        List<Product> products = productService.findAll(size, page);
        String json = new Gson().toJson(products);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(json);
            resp.setStatus(200);
        }
    }
}
