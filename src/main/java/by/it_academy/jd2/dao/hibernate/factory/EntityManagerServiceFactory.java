package by.it_academy.jd2.dao.hibernate.factory;

import by.it_academy.jd2.dao.connection.api.IEntityManagerService;
import by.it_academy.jd2.dao.hibernate.EntityManagerService;

public class EntityManagerServiceFactory {
    private static final IEntityManagerService entityManagerService = new EntityManagerService();

    private EntityManagerServiceFactory(){}

    public static IEntityManagerService getEntityManagerService() {
        return entityManagerService;
    }
}
