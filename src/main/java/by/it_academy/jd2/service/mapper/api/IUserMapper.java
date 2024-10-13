package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.dao.entity.UserEntity;

public interface IUserMapper {

    UserReadDto mapEntityToDto(UserEntity userEntity);

    UserEntity mapDtoToEntity(UserCreateDto userCreateDto);

}
