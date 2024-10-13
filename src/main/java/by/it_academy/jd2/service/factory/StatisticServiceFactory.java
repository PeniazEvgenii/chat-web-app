package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.service.StatisticService;
import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.storage.CountSessionStorageFactory;

public class StatisticServiceFactory {
    private static final IStatisticsService statisticService = new StatisticService(
            CountSessionStorageFactory.getCountSessionStorage(),
            UserServiceFactory.getUserService(),
            MessageServiceFactory.getMessageService()
    );

    private StatisticServiceFactory() {};

    public static IStatisticsService getStatisticService() {
        return statisticService;
    }
}
