package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.StatisticDto;
import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.storage.UserStorage;
import by.it_academy.jd2.storage.api.IUserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticService implements IStatisticsService {
    private static final IStatisticsService INSTANCE = new StatisticService();
    private final IUserStorage userStorage = UserStorage.getInstance();

    List<String> activeSession = new ArrayList<>();

    private StatisticService() {}

    public static IStatisticsService getInstance() {
        return INSTANCE;
    }

    //из листнера на сохранение
    public void saveFromSession(UserDto userDto, String sessionId) {
        String login = userDto.getLogin();
        activeSession.add(sessionId);
    }

    //из листнера на удаление
    public boolean deleteFromSession(String sessionId) {
       return activeSession.remove(sessionId);
    }

    public int getCountAllUsers() {
       return userStorage.getAll().size();
    }

    public int getActiveUsers() {
        return activeSession.size();
    }

    public StatisticDto get() {
        return StatisticDto.builder()
                .setCountActiveUsers(getActiveUsers())
                .setCountAllUsers(getCountAllUsers())
                .setCountMessages(1000)                       //поменять!!!! и логины можно передать
                .build();
    }
}
