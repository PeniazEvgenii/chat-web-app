package by.it_academy.jd2.mapper;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.entity.ERole;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.api.IUserMapper;
import by.it_academy.jd2.util.DateFormatUtil;

public class UserMapper implements IUserMapper {
    @Override
    public UserDto mapEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .setId(userEntity.getId())
                .setLogin(userEntity.getLogin())
                .setPassword(userEntity.getPassword())
                .setName(userEntity.getName())
                .setBirthDate(userEntity.getBirthDate())
                .setRegistrationDate(userEntity.getRegistrationDate())
                .setRole(ERole.getRoleByName(userEntity.getRole()).orElse(null))
                .build();
    }

    @Override
    public UserEntity mapDtoToEntity(UserCreateDto userCreateDto) {
        return UserEntity.builder()
                .setLogin(userCreateDto.getLogin())
                .setPassword(userCreateDto.getPassword())
                .setName(userCreateDto.getName())
                .setBirthDate(DateFormatUtil.parseDateFromString(userCreateDto.getBirthDate()))
                .setRegistrationDate(userCreateDto.getRegistrationDate())
                .setRole(userCreateDto.getRole().name())
                .build();
    }
}
