package by.it_academy.jd2.dao.api;

import by.it_academy.jd2.entity.MessageEntity;

import java.util.List;
import java.util.UUID;

public interface IMessageDao {

    MessageEntity create(MessageEntity message);

    List<MessageEntity> getByUserTo(UUID userIdTo);

    List<MessageEntity> getAll();

    void update(MessageEntity messageEntity);

    Long getCount();
}
