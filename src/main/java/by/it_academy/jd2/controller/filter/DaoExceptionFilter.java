package by.it_academy.jd2.controller.filter;


import by.it_academy.jd2.dao.exception.DaoException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class DaoExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (DaoException e) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Произошла ошибка на сервере. Обратитесь к администратору. " + e.getMessage());
        }
    }
}
