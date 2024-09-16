package by.it_academy.jd2.dao.factory;

import by.it_academy.jd2.dao.MessageDao;
import by.it_academy.jd2.dao.connection.factory.ConnectionManagerFactory;

public class MessageDaoFactory {      //переделать
    private static final MessageDao instance = new MessageDao(ConnectionManagerFactory.getInstance());   //добавить интерфейс

    private MessageDaoFactory() {}

    public static MessageDao getInstance() {
        return instance;
    }
}
