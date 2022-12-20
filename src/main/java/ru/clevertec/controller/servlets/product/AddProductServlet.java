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

/**
 * Servlet добавить продукт.
 *
 * @author Ловцов Алексей
 */
@WebServlet("/api/product/add")
public class AddProductServlet extends HttpServlet {

    /**
     * Сервис продукта.
     */
    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        Product product = new Product(name, price);
        productService.save(product);
        String json = new Gson().toJson(product);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(json);
            resp.setContentType("application/json");
            resp.setStatus(201);
        }
    }
}
