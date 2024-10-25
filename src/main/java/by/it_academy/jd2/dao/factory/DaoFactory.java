package by.it_academy.jd2.dao.factory;

import by.it_academy.jd2.dao.MessageDao;
import by.it_academy.jd2.dao.UserDao;
import by.it_academy.jd2.dao.api.IMessageDao;
import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.factory.ConnectionManagerFactory;
import by.it_academy.jd2.dao.hibernate.MessageDaoHibernate;
import by.it_academy.jd2.dao.hibernate.UserDaoHibernate;
import by.it_academy.jd2.dao.hibernate.factory.EntityManagerServiceFactory;

public class DaoFactory {
    private static final IUserDao userDao = new UserDao(ConnectionManagerFactory.getInstance());
    private static final IMessageDao messageDao = new MessageDao(ConnectionManagerFactory.getInstance(), userDao);

    private static final IUserDao userDaoHibernate = new UserDaoHibernate(EntityManagerServiceFactory.getEntityManagerService());
    private static final IMessageDao messageDaoHibernate = new MessageDaoHibernate(EntityManagerServiceFactory.getEntityManagerService());

    private DaoFactory() {}

    public static IUserDao getUserDao() {
        return userDao;
    }
    public static IMessageDao getMessageDao() {
        return messageDao;
    }

    public static IUserDao getUserDaoHibernate() {
        return userDaoHibernate;
    }
    public static IMessageDao getMessageDaoHibernate() {
        return messageDaoHibernate;
    }
}
