package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.connection.ConnectionManager;
import by.it_academy.jd2.dao.connection.factory.ConnectionManagerFactory;
import by.it_academy.jd2.dao.factory.MessageDaoFactory;
import by.it_academy.jd2.dto.UserForMassageEntity;
import by.it_academy.jd2.entity.MessageEntity;
import by.it_academy.jd2.entity.MessageEntity2;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class TestDao {
    public static void main(String[] args) {
        MessageDao messageDao = MessageDaoFactory.getInstance();

        List<MessageEntity> byUserTo = messageDao.getByUserTo(UUID.fromString("11350b5b-5d01-4812-8010-1be53acb47f3"));
        for (MessageEntity messageEntity : byUserTo) {
            System.out.println(messageEntity.getUserTo() +"  " + messageEntity.getUserFrom());
        }
        System.out.println(byUserTo);

        ConnectionManagerFactory.getInstance().close();

    }


}
