package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.dto.MessageCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.factory.MessageServiceFactory;
import by.it_academy.jd2.service.validation.exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import static by.it_academy.jd2.util.PathUtil.*;

@WebServlet(urlPatterns = MESSAGE_SERVLET)
public class MessageServlet extends HttpServlet {
    public static final String SESSION_ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_ERRORS = "errors";
    public static final String ATTRIBUTE_FINISH = "finish";
    public static final String PARAMETER_USERNAME = "username";
    public static final String PARAMETER_MESSAGE = "message";
    public static final String MESSAGE_SUCCESS_SEND = "Сообщение отправлено";

    private final IMessageService messageService = MessageServiceFactory.getMessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageCreateDto message = MessageCreateDto.builder()
                .setUsername(req.getParameter(PARAMETER_USERNAME))
                .setBody(req.getParameter(PARAMETER_MESSAGE))
                .setUserFrom((UserReadDto) req.getSession().getAttribute(SESSION_ATTRIBUTE_USER))
                .build();

        try {
            messageService.create(message);
            req.setAttribute(ATTRIBUTE_FINISH, MESSAGE_SUCCESS_SEND);
        } catch (ValidationException e) {
            req.setAttribute(ATTRIBUTE_ERRORS, e.getErrors());
        }
        doGet(req, resp);
    }
}
