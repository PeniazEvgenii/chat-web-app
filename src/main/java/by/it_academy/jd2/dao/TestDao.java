package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.factory.MessageDaoFactory;
import by.it_academy.jd2.dto.UserForMassageEntity;
import by.it_academy.jd2.entity.MessageEntity;
import by.it_academy.jd2.entity.MessageEntity2;

import java.time.OffsetDateTime;
import java.util.List;

public class TestDao {
    public static void main(String[] args) {
        MessageDao messageDao = MessageDaoFactory.getInstance();
//        List<MessageEntity2> byUserTo2 = messageDao.getByUserTo2(2L);
//        byUserTo2.forEach(m -> System.out.println(m.getUserTo()));
        System.out.println(messageDao.getCount());

        List<MessageEntity> byUserTo = messageDao.getByUserTo(2L);
        for (MessageEntity messageEntity : byUserTo) {
            System.out.println(messageEntity.getUserFrom().getLogin());
            System.out.print("    ");
            System.out.println(messageEntity.getUserTo().getLogin());
            System.out.println();
        }


    }
}
