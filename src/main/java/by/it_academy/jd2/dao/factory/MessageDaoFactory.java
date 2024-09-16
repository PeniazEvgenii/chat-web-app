package by.it_academy.jd2.dao.factory;

import by.it_academy.jd2.dao.MessageDao;

public class MessageDaoFactory {
    private static final MessageDao instance = new MessageDao();   //добавить интерфейс

    private MessageDaoFactory() {}

    public static MessageDao getInstance() {
        return instance;
    }
}
