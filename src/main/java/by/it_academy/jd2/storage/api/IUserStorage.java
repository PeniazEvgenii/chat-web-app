package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;

import java.util.Map;
import java.util.Optional;

public interface IUserStorage {

    Long create(UserEntity userEntity);

    Optional<UserEntity> getById(Long id);

    Map<Long, UserEntity> getAll();

    Optional<UserEntity> getUserByPassLogin (UserLoginDto userLoginDto);
}
