package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.StatisticDto;
import by.it_academy.jd2.dto.UserDto;

public interface IStatisticsService {

    void saveFromSession(UserDto userDto, String sessionId);

    boolean deleteFromSession(String sessionId);

    StatisticDto get();
}
