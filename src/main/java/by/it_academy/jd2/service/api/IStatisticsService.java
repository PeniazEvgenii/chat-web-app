package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.StatisticDto;
import by.it_academy.jd2.dto.UserReadDto;

public interface IStatisticsService {

    void saveFromSession(UserReadDto userReadDto, String sessionId);

    boolean deleteFromSession(String sessionId);

    StatisticDto get();
}
