package by.it_academy.jd2.controller.listeners;

import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.factory.StatisticServiceFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionChangeListener implements HttpSessionAttributeListener {
    private static final String SESSION_ATTRIBUTE_USER = "user";

    private final IStatisticsService statisticsService = StatisticServiceFactory.getStatisticService();


    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if(event.getName().equalsIgnoreCase(SESSION_ATTRIBUTE_USER)) {
            UserReadDto userReadDto = (UserReadDto) event.getValue();  // для статистики можно будет
            String sessionId = event.getSession().getId();             // для статистики можно будет

            statisticsService.saveFromSession();
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if(se.getName().equals(SESSION_ATTRIBUTE_USER)) {
            statisticsService.deleteFromSession();
        }
    }
}
