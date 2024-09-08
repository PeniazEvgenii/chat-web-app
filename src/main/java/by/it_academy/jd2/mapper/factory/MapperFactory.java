package by.it_academy.jd2.mapper.factory;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.MapperToUserDto;
import by.it_academy.jd2.mapper.MapperToUserEntity;
import by.it_academy.jd2.mapper.api.IMapper;

public class MapperFactory {
    private static final IMapper<UserCreateDto, UserEntity> mapperToUserEntityInstance = new MapperToUserEntity();

    private static final IMapper<UserEntity, UserDto> mapperToUserDtoInstance = new MapperToUserDto();


    private MapperFactory() {}

    public static IMapper<UserCreateDto, UserEntity> getMapperToUserEntityInstance() {
        return mapperToUserEntityInstance;
    }

    public static IMapper<UserEntity, UserDto> getMapperToUserDtoInstance() {
        return mapperToUserDtoInstance;
    }
}
