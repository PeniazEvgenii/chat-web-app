package by.it_academy.jd2.mapper.api;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.entity.UserEntity;

public interface IUserMapper {

    UserDto mapEntityToDto(UserEntity userEntity);

    UserEntity mapDtoToEntity(UserCreateDto userCreateDto);

}
