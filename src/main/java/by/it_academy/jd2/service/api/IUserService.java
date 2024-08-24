package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.entity.UserEntity;

import java.util.Map;

public interface IUserService {
    Long create(UserCreateDto userCreateDto);

    Map<Long, UserEntity> getAllUsers();

}
