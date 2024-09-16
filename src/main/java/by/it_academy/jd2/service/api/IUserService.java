package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserService {
    Long create(UserCreateDto userCreateDto);

    List<UserEntity> getAll();

    Optional<UserDto> login(UserLoginDto userLoginDto);

}
