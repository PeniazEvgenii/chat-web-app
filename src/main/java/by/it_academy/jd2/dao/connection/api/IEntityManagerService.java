package by.it_academy.jd2.dao.connection.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.function.Function;

public interface IEntityManagerService {
    EntityManager getEntityManager();

    EntityManagerFactory getEntityManagerFactory();

    public <T> T inTransaction(Function<EntityManager, T> work);

    void close();
}
