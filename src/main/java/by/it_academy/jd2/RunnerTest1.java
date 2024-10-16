package by.it_academy.jd2;

import by.it_academy.jd2.dao.api.IMessageDao;
import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.entity.MessageEntity;
import by.it_academy.jd2.dao.entity.UserEntity;
import by.it_academy.jd2.dao.factory.DaoFactory;
import by.it_academy.jd2.service.dto.UserLoginDto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RunnerTest1 {
    public static void main(String[] args) {
        IUserDao userDaoHibernate = DaoFactory.getUserDaoHibernate();
        IMessageDao messageDaoHibernate = DaoFactory.getMessageDaoHibernate();


        Optional<UserEntity> a1 = userDaoHibernate.getById(UUID.fromString("11350b5b-5d01-4812-8010-1be53acb47f3"));
        Optional<UserEntity> a2 = userDaoHibernate.getById(UUID.fromString("b100ca71-a979-477a-a66d-81c8f4dc2776"));

        List<MessageEntity> all = messageDaoHibernate.getByUserTo(UUID.fromString("b100ca71-a979-477a-a66d-81c8f4dc2776"));

        messageDaoHibernate.create(MessageEntity.builder()
                        .setId(UUID.randomUUID())
                        .setUserFrom(a1.get())
                        .setUserTo(a2.get())
                        .setBody("Test time")
                        .setCreateAt(OffsetDateTime.now())
                        .setUpdateAt(OffsetDateTime.now())
                .build());

        for (MessageEntity messageEntity : all) {
            System.out.println(messageEntity.getCreateAt());
            System.out.println(messageEntity.getUserFrom());
            System.out.println(messageEntity.getUserTo());
            System.out.println("-----------------------------------");
        }
    }
}
