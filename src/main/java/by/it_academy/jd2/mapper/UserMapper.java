package by.it_academy.jd2.mapper;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserReadDto;
import by.it_academy.jd2.entity.ERole;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.api.IUserMapper;
import by.it_academy.jd2.util.DateFormatUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserMapper implements IUserMapper {
    @Override
    public UserReadDto mapEntityToDto(UserEntity userEntity) {
        return UserReadDto.builder()
                .setId(userEntity.getId())
                .setLogin(userEntity.getLogin())
                .setPassword(userEntity.getPassword())
                .setName(userEntity.getName())
                .setBirthDate(userEntity.getBirthDate())
                .setRegistrationAt(userEntity.getRegistrationAt())
                .setUpdateAt(userEntity.getUpdateAt())
                .setRole(ERole.getRoleByName(userEntity.getRole()).orElse(null))
                .build();
    }

    @Override
    public UserEntity mapDtoToEntity(UserCreateDto userCreateDto) {
        return UserEntity.builder()
                .setId(UUID.randomUUID())
                .setLogin(userCreateDto.getLogin())
                .setPassword(userCreateDto.getPassword())
                .setName(userCreateDto.getName())
                .setBirthDate(DateFormatUtil.parseDateFromString(userCreateDto.getBirthDate()))
                .setRegistrationAt(LocalDateTime.now())
                .setUpdateAt(LocalDateTime.now())
                .setRole(userCreateDto.getRole().name())
                .build();
    }
}
