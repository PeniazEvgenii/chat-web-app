package by.it_academy.jd2.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.it_academy.jd2.util.PathUtil.LOGIN_SERVLET;
import static by.it_academy.jd2.util.PathUtil.LOGOUT_SERVLET;

@WebServlet(urlPatterns = LOGOUT_SERVLET)
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + LOGIN_SERVLET);
    }
}
