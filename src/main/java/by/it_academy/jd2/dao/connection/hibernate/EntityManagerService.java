package by.it_academy.jd2.dao.connection.hibernate;

import by.it_academy.jd2.dao.connection.api.IEntityManagerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Function;

public class EntityManagerService implements IEntityManagerService {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public <T> T inTransaction(Function<EntityManager, T> work) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T result = work.apply(entityManager);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void close() {
        if(entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}
