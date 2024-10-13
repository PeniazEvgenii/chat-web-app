package by.it_academy.jd2.service;

import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.StatisticDto;
import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.storage.ICountSessionStorage;

public class StatisticService implements IStatisticsService {

     private final ICountSessionStorage countSessionStorage;
     private final IUserService userService;
     private final IMessageService messageService;

    public StatisticService(ICountSessionStorage countSessionStorage, IUserService userService, IMessageService messageService) {
        this.countSessionStorage = countSessionStorage;
        this.userService = userService;
        this.messageService = messageService;
    }

    public void saveFromSession() {
        countSessionStorage.addActive();
    }

    public void deleteFromSession() {
        countSessionStorage.remove();
    }

    public StatisticDto get() {
        return StatisticDto.builder()
                .setCountActiveUsers(getActiveUsers())
                .setCountAllUsers(getCountAllUsers())
                .setCountMessages(getCountAllMessages())
                .build();
    }

    public Long getCountAllUsers() {
       return userService.getCount();
    }

    public Long getCountAllMessages() {
        return messageService.getCount();
    }


    public Long getActiveUsers() {
        return countSessionStorage.getCountActiveUsers();
    }
}
