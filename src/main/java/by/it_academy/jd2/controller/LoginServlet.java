package by.it_academy.jd2.controller;

import by.it_academy.jd2.util.JspUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.it_academy.jd2.util.PathUtil.LOGIN_JSP;
import static by.it_academy.jd2.util.PathUtil.LOGIN_SERVLET;

@WebServlet(urlPatterns = LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JspUtil.getPath(LOGIN_JSP)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);

        req.getSession().setAttribute("user", "user");    //тест

        try {
            throw new RuntimeException();                            //тест
        } catch (RuntimeException e) {
            req.setAttribute("error", "Неверное имя пользователя или пароль"); //тест
            resp.sendRedirect(req.getContextPath() + "/api/login?error=1&login=" + login);
        }
    }
}
