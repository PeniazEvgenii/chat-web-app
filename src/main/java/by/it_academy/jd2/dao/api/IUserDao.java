package by.it_academy.jd2.dao.api;

import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.dao.entity.UserEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserDao {

    UserEntity create(UserEntity user);

    void delete(UserEntity user);

    Long getCount();

    Optional<UserEntity> getById(UUID id);

    Optional<UserEntity> getById(UUID id, Connection connection);

    List<UserEntity> getAll();

    Optional<UserEntity> getByPassLogin(UserLoginDto userLoginDto);

    Optional<UserEntity> getByLogin(String login);
}
