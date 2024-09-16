package by.it_academy.jd2.dao.api;

import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserDao {

    UserEntity create(UserEntity user);

    boolean delete(Long id);

    Optional<UserEntity> getById(Long id);

    List<UserEntity> getAll();

    Optional<UserEntity> getUserByPassLogin (UserLoginDto userLoginDto);

    Optional<UserEntity> getUserByLogin (String login);
}
