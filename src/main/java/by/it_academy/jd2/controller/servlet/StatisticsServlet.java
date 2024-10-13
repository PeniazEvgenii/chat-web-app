package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.service.dto.StatisticDto;
import by.it_academy.jd2.service.factory.StatisticServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.it_academy.jd2.util.PathUtil.*;

@WebServlet(urlPatterns = STATISTICS_SERVLET)
public class StatisticsServlet extends HttpServlet {
    public static final String ATTRIBUTE_STATISTICS = "statistics";

    private final IStatisticsService statisticsService = StatisticServiceFactory.getStatisticService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StatisticDto statisticDto = statisticsService.get();
        req.setAttribute(ATTRIBUTE_STATISTICS, statisticDto);

        req.getRequestDispatcher(STATISTICS_JSP).forward(req, resp);
    }
}
