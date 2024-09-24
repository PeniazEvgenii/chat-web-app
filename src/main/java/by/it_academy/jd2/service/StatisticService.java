package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.StatisticDto;
import by.it_academy.jd2.dto.UserReadDto;
import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.storage.api.IUserStorage;

import java.util.ArrayList;
import java.util.List;

public class StatisticService implements IStatisticsService {            // переделать

    private final IUserStorage userStorage;

    List<String> activeSession = new ArrayList<>();

    public StatisticService(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }


    //из листнера на сохранение
    public void saveFromSession(UserReadDto userReadDto, String sessionId) {
        String login = userReadDto.getLogin();
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
