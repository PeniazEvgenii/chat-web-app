package by.it_academy.jd2.dao.factory;

import by.it_academy.jd2.dao.UserDao;
import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.factory.ConnectionManagerFactory;

public class UserDaoFactory {
    private static final IUserDao instance = new UserDao(ConnectionManagerFactory.getInstance());

    private UserDaoFactory() {}

    public static IUserDao getInstance() {
        return instance;
    }
}
