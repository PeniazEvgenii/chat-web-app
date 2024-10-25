package by.it_academy.jd2.dao.hibernate;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.api.IEntityManagerService;
import by.it_academy.jd2.dao.entity.UserEntity;
import by.it_academy.jd2.dao.entity.UserEntity_;
import by.it_academy.jd2.service.dto.UserLoginDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDaoHibernate implements IUserDao {
    private final IEntityManagerService entityManagerService;

    public UserDaoHibernate(IEntityManagerService entityManagerService) {
        this.entityManagerService = entityManagerService;
    }

    @Override
    public UserEntity create(UserEntity user) {
       return entityManagerService.inTransaction(entityManager -> {
           entityManager.persist(user);
           return user;
       });
    }

    @Override
    public Optional<UserEntity> getById(UUID id) {
        return Optional.ofNullable(
                entityManagerService.inTransaction(
                entityManager -> entityManager.find(UserEntity.class, id))
        );
    }

    @Override
    public List<UserEntity> getAll() {
       return entityManagerService.inTransaction(entityManager -> {
           CriteriaBuilder cb = entityManager.getCriteriaBuilder();
           CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);
           Root<UserEntity> entity = criteria.from(UserEntity.class);
           criteria.select(entity);

           return entityManager.createQuery(criteria).getResultList();
       });
    }

    @Override
    public Optional<UserEntity> getByPassLogin(UserLoginDto userLoginDto) {
        return Optional.ofNullable(entityManagerService.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);
            Root<UserEntity> userEntity = criteria.from(UserEntity.class);
            criteria.select(userEntity)
                    .where(cb.equal(userEntity.get(UserEntity_.login), userLoginDto.getLogin()),
                           cb.equal(userEntity.get(UserEntity_.password), userLoginDto.getPassword()));
            return entityManager.createQuery(criteria).getResultStream().findFirst().orElse(null);
        }));
    }

    @Override
    public Optional<UserEntity> getByLogin(String login) {
        return Optional.ofNullable(entityManagerService.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);
            Root<UserEntity> userEntity = criteria.from(UserEntity.class);
            criteria.select(userEntity)
                    .where(cb.equal(userEntity.get(UserEntity_.login), login));
            return entityManager.createQuery(criteria).getResultStream().findFirst().orElse(null);
        }));
    }

    public Long getCount() {
        return entityManagerService.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
            Root<UserEntity> userEntity = criteria.from(UserEntity.class);
            criteria.select(cb.count(userEntity.get(UserEntity_.id)));
            return entityManager.createQuery(criteria).getSingleResult();
        });
    }

    @Override
    public void delete(UserEntity user) {
        entityManagerService.inTransaction(entityManager -> {
            entityManager.remove(user);
            return user;
        });
    }

    @Override
    public Optional<UserEntity> getById(UUID id, Connection connection) {
        return Optional.empty();
    }

}
