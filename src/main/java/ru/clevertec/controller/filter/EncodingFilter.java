package ru.clevertec.controller.filter;

import lombok.SneakyThrows;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Кодирующий фильтр.
 *
 * @author Ловцов Алексей
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
