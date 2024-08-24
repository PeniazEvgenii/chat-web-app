package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserCreateDto;

public interface IUserService {
    Long create(UserCreateDto userCreateDto);

}
