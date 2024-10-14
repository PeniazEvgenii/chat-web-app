package by.it_academy.jd2.dao.connection.hibernate;

import by.it_academy.jd2.dao.api.IMessageDao;
import by.it_academy.jd2.dao.connection.api.IEntityManagerService;
import by.it_academy.jd2.dao.entity.MessageEntity;
import by.it_academy.jd2.dao.entity.MessageEntity_;
import by.it_academy.jd2.dao.entity.UserEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class MessageDaoHibernate implements IMessageDao {
    private final IEntityManagerService entityManagerService;

    public MessageDaoHibernate(IEntityManagerService entityManagerService) {
        this.entityManagerService = entityManagerService;
    }

    public MessageEntity create(MessageEntity message) {
        return entityManagerService.inTransaction(entityManager -> {
            entityManager.persist(message);
            return message;
        });
    }

    public List<MessageEntity> getByUserTo(UUID userIdTo) {
        return entityManagerService.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<MessageEntity> criteria = cb.createQuery(MessageEntity.class);
            Root<MessageEntity> messageEntity = criteria.from(MessageEntity.class);
            messageEntity.fetch(MessageEntity_.userTo);
            messageEntity.fetch(MessageEntity_.userFrom);
            criteria.select(messageEntity)
                    .where(cb.equal(messageEntity.get(MessageEntity_.userTo).get(UserEntity_.id), userIdTo))
                    .orderBy(cb.desc(messageEntity.get(MessageEntity_.CREATE_AT)));
            return entityManager.createQuery(criteria).getResultList();
        });
    }

    public List<MessageEntity> getAll() {
        return entityManagerService.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<MessageEntity> criteria = cb.createQuery(MessageEntity.class);
            Root<MessageEntity> messageEntity = criteria.from(MessageEntity.class);
            messageEntity.fetch(MessageEntity_.userTo);
            messageEntity.fetch(MessageEntity_.userFrom);
            criteria.select(messageEntity);
            return entityManager.createQuery(criteria).getResultList();
        });
    }

    public Long getCount() {
        return entityManagerService.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
            Root<MessageEntity> messageEntity = criteria.from(MessageEntity.class);
            criteria.select(cb.count(messageEntity.get(MessageEntity_.id)));
            return entityManager.createQuery(criteria).getSingleResult();
        });
    }

    public void update(MessageEntity messageEntity) {
        entityManagerService.inTransaction(entityManager -> {
            entityManager.persist(messageEntity);
            return messageEntity;
        });
    }



}
