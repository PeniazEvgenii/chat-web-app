package by.it_academy.jd2.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.it_academy.jd2.util.PathUtil.START_JSP;
import static by.it_academy.jd2.util.PathUtil.START_SERVLET;

@WebServlet(urlPatterns = START_SERVLET)
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(START_JSP).forward(req, resp);
    }
}
