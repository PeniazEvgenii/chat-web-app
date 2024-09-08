package by.it_academy.jd2.mapper;

import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.entity.ERole;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.api.IMapper;

public class MapperToUserDto implements IMapper<UserEntity, UserDto>{

    public MapperToUserDto() {}

    @Override
    public UserDto mapFrom(UserEntity userEntity) {
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

}
