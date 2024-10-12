package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserReadDto;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserEntity create(UserCreateDto userCreateDto);

    List<UserReadDto> getAll();

    Long getCount();

    Optional<UserReadDto> login(UserLoginDto userLoginDto);

    Optional<UserReadDto> getByLogin(String login);

    Optional<UserEntity> getEntityByLogin(String login);

}
