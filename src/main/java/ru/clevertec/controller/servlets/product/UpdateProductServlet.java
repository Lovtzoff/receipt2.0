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
 * Servlet обновить продукт.
 */
@WebServlet("/api/product/update")
public class UpdateProductServlet extends HttpServlet {

    /**
     * Сервис продукта.
     */
    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        Product product = new Product(name, price);
        productService.update(product, id);
        String json = new Gson().toJson(product);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(json);
            resp.setStatus(201);
        }
    }
}
