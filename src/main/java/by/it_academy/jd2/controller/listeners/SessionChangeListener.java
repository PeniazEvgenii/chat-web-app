package by.it_academy.jd2.controller.listeners;

import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.service.StatisticService;
import by.it_academy.jd2.service.api.IStatisticsService;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionChangeListener implements HttpSessionAttributeListener {
    private final IStatisticsService statisticsService = StatisticService.getInstance();


    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if(event.getName().equals("user")) {
            UserDto userDto = (UserDto) event.getValue();
            event.getSession().getAttributeNames();
            String id = event.getSession().getId();
            statisticsService.saveFromSession(userDto, id);
        }



    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if(se.getName().equals("user")) {
            statisticsService.deleteFromSession(se.getSession().getId());
        }

    }
}
