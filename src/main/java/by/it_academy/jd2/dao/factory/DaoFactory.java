package by.it_academy.jd2.dao.factory;

import by.it_academy.jd2.dao.MessageDao;
import by.it_academy.jd2.dao.UserDao;
import by.it_academy.jd2.dao.api.IMessageDao;
import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.factory.ConnectionManagerFactory;

public class DaoFactory {
    private static final IUserDao userDao = new UserDao(ConnectionManagerFactory.getInstance());
    private static final IMessageDao messageDao = new MessageDao(ConnectionManagerFactory.getInstance(), userDao);

    private DaoFactory() {}

    public static IUserDao getUserDao() {
        return userDao;
    }

    public static IMessageDao getMessageDao() {
        return messageDao;
    }
}
