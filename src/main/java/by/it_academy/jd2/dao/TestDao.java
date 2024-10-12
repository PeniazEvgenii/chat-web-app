package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IMessageDao;
import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.factory.ConnectionManagerFactory;
import by.it_academy.jd2.dao.factory.DaoFactory;
import by.it_academy.jd2.entity.MessageEntity;
import by.it_academy.jd2.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class TestDao {
    public static void main(String[] args) {
        IMessageDao messageDao = DaoFactory.getMessageDao();
        IUserDao userDao = DaoFactory.getUserDao();

        for (MessageEntity messageEntity : messageDao.getAll()) {
            System.out.println(messageEntity);
        }


//        List<MessageEntity> byUserTo = messageDao.getByUserTo(UUID.fromString("11350b5b-5d01-4812-8010-1be53acb47f3"));
//
//        UserEntity ntity2 = userDao.getById(UUID.fromString("11350b5b-5d01-4812-8010-1be53acb47f3")).get();
//        UserEntity ntity1 = userDao.getById(UUID.fromString("b100ca71-a979-477a-a66d-81c8f4dc2776")).get();
//
//        for (MessageEntity messageEntity : byUserTo) {
//            System.out.println(messageEntity.getUserTo() +"  " + messageEntity.getUserFrom());
//        }
//        System.out.println(byUserTo);
//
//        messageDao.create(MessageEntity.builder()
//                        .setBody("4 сообщение")
//                        .setUserTo(ntity1)
//                        .setUserFrom(ntity2)
//                        .setCreateAt(OffsetDateTime.now())
//                        .setUpdateAt(LocalDateTime.now())
//                        .setId(UUID.randomUUID())
//                .build());

        ConnectionManagerFactory.getInstance().close();

    }


}
