package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.service.StatisticService;
import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.storage.factory.UserStorageFactory;

public class StatisticServiceFactory {
    private static final IStatisticsService INSTANCE = new StatisticService(UserStorageFactory.getInstance());

    private StatisticServiceFactory() {};

    public static IStatisticsService getInstance() {
        return INSTANCE;
    }
}
