package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.util.JspUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.it_academy.jd2.controller.RegistrationServlet.PARAMETER_LOGIN;
import static by.it_academy.jd2.controller.RegistrationServlet.PARAMETER_PASSWORD;
import static by.it_academy.jd2.util.PathUtil.LOGIN_JSP;
import static by.it_academy.jd2.util.PathUtil.LOGIN_SERVLET;

@WebServlet(urlPatterns = LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {
    public static final String SESSION_USER = "user";

    private final IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspUtil.getPath(LOGIN_JSP)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginDto userLoginDto = new UserLoginDto(req.getParameter(PARAMETER_LOGIN),
                                                     req.getParameter(PARAMETER_PASSWORD));

        userService.login(userLoginDto).ifPresentOrElse(
                userDto -> loginSuccess(req, resp, userDto),
                () -> loginFail(req, resp)
        );
    }

    private void loginSuccess(HttpServletRequest req, HttpServletResponse resp, UserDto userDto) {
        req.getSession().setAttribute(SESSION_USER, userDto);
        try {
            req.setAttribute("access", "Login is successfully");
            doGet(req, resp);
            //resp.sendRedirect(req.getContextPath() + LOGIN_SERVLET);          // пока на логин
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loginFail(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(PARAMETER_LOGIN);
        try {
            resp.sendRedirect(req.getContextPath() + LOGIN_SERVLET + "?error=1&login=" + login);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
