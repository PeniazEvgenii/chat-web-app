package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.dto.MessageByUserDto;
import by.it_academy.jd2.service.dto.MessageDateFormat;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.factory.MessageServiceFactory;
import by.it_academy.jd2.service.mapper.api.IMessageMapper;
import by.it_academy.jd2.service.mapper.factory.MapperFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.it_academy.jd2.util.PathUtil.CHATS_JSP;
import static by.it_academy.jd2.util.PathUtil.CHAT_SERVLET;

@WebServlet(CHAT_SERVLET)
public class ChatServlet extends HttpServlet {
    public static final String ATTRIBUTE_MESSAGES = "messages";
    public static final String SESSION_ATTRIBUTE_TIMEZONE = "tz";
    public static final String SESSION_ATTRIBUTE_USER = "user";

    private final IMessageService messageService = MessageServiceFactory.getMessageService();
    private final IMessageMapper messageMapper = MapperFactory.getMessageMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ZoneId zoneId = Optional.ofNullable(
                        (ZoneId) session.getAttribute(SESSION_ATTRIBUTE_TIMEZONE))
                        .orElse(ZoneId.systemDefault());
        UserReadDto user = (UserReadDto) session.getAttribute(SESSION_ATTRIBUTE_USER);

        List<MessageDateFormat> allMessageTo = messageService
                .getAllByUserTo(new MessageByUserDto(user.getId(), zoneId))
                .stream()
                .map(messageMapper::mapFrom)
                .collect(Collectors.toList());

        req.setAttribute(ATTRIBUTE_MESSAGES, allMessageTo);
        req.getRequestDispatcher(CHATS_JSP).forward(req, resp);
    }
}
