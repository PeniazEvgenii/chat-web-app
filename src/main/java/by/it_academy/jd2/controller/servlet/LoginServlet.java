package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;

import static by.it_academy.jd2.controller.servlet.RegistrationServlet.PARAMETER_LOGIN;
import static by.it_academy.jd2.controller.servlet.RegistrationServlet.PARAMETER_PASSWORD;
import static by.it_academy.jd2.util.PathUtil.*;

@WebServlet(urlPatterns = LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {
    public static final String SESSION_ATTRIBUTE_USER = "user";
    public static final String SESSION_ATTRIBUTE_TIMEZONE = "tz";
    public static final String REQUEST_PARAMETER_TIMEZONE = "timezone";
    public static final String REQUEST_ATTRIBUTE_ACCESS = "access";

    private final IUserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timezone = req.getParameter(REQUEST_PARAMETER_TIMEZONE);
        if(timezone != null && !timezone.isBlank()) {
            req.getSession().setAttribute(SESSION_ATTRIBUTE_TIMEZONE, ZoneId.of(timezone));
        }

        UserLoginDto userLoginDto = new UserLoginDto(req.getParameter(PARAMETER_LOGIN),
                                                     req.getParameter(PARAMETER_PASSWORD));

        userService.login(userLoginDto).ifPresentOrElse(
                userDto -> loginSuccess(req, resp, userDto),
                () -> loginFail(req, resp)
        );
    }

    private void loginSuccess(HttpServletRequest req, HttpServletResponse resp, UserReadDto userReadDto) {
        req.getSession().setAttribute(SESSION_ATTRIBUTE_USER, userReadDto);
        try {
            req.setAttribute(REQUEST_ATTRIBUTE_ACCESS , "Login is successfully");         //можно сделать проверкку на уже установленный в сессии user
            doGet(req, resp);
            //resp.sendRedirect(req.getContextPath() + LOGIN_SERVLET);          // пока на логин, а так можно избавиться от exception
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
