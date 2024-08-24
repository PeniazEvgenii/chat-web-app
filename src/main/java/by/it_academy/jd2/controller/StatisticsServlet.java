package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.StatisticDto;
import by.it_academy.jd2.service.StatisticService;
import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.util.JspUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/api/admin/statistics")
public class StatisticsServlet extends HttpServlet {
    private final IStatisticsService statisticsService = StatisticService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> test = new HashMap<>();

        StatisticDto statisticDto = statisticsService.get();

        test.put("Количество активных пользователей", statisticDto.getCountActiveUsers());
        test.put("Количество пользователей в приложении", statisticDto.getCountAllUsers());
        test.put("Количество сообщений отправленных в приложении", statisticDto.getCountMessage());
        req.setAttribute("statistics", test);

        req.getRequestDispatcher(JspUtil.getPath("admin/statistics")).forward(req, resp);
    }
}
