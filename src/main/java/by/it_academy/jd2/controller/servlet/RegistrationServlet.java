package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.entity.ERole;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.util.JspUtil;
import by.it_academy.jd2.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

import static by.it_academy.jd2.util.PathUtil.*;

@WebServlet(urlPatterns = REGISTRATION_SERVLET)
public class RegistrationServlet extends HttpServlet {
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_BIRTHDAY = "birthday";
    public static final String ATTRIBUTE_ERRORS = "errors";
    public static final String ATTRIBUTE_ENTER_LOGIN = "enterLogin";


    private final IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JspUtil.getPath(REGISTRATION_JSP)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .setLogin(req.getParameter(PARAMETER_LOGIN))
                .setPassword(req.getParameter(PARAMETER_PASSWORD))
                .setName(req.getParameter(PARAMETER_NAME))
                .setBirthDate(req.getParameter(PARAMETER_BIRTHDAY))
                .setRegisterDate(LocalDate.now())
                .setRole(ERole.USER)
                .build();

        try {
            userService.create(userCreateDto);
            resp.sendRedirect(req.getContextPath() + LOGIN_SERVLET);
        } catch (ValidationException e) {
            req.setAttribute(ATTRIBUTE_ERRORS, e.getErrors());
            req.setAttribute(ATTRIBUTE_ENTER_LOGIN, req.getParameter(PARAMETER_LOGIN));
            doGet(req, resp);
        }
    }
}
