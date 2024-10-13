package by.it_academy.jd2.service.api;

import by.it_academy.jd2.service.dto.StatisticDto;

public interface IStatisticsService {

    void saveFromSession();

    void deleteFromSession();

    Long getCountAllUsers();

    Long getCountAllMessages();

    StatisticDto get();

    Long getActiveUsers();
}
