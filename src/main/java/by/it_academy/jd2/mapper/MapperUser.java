package by.it_academy.jd2.mapper;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.api.IMapper;
import by.it_academy.jd2.util.DateFormatUtil;

public class MapperUser implements IMapper<UserCreateDto, UserEntity> {
    public static final IMapper<UserCreateDto, UserEntity> INSTANCE = new  MapperUser();

    private MapperUser(){}

    @Override
    public UserEntity mapFrom(UserCreateDto userCreateDto) {
        return UserEntity.builder()
                .setLogin(userCreateDto.getLogin())
                .setPassword(userCreateDto.getPassword())
                .setName(userCreateDto.getName())
                .setBirthDate(DateFormatUtil.parseDateFromString(userCreateDto.getBirthDate()))
                .setRegistrationDate(userCreateDto.getRegistrationDate())
                .setRole(userCreateDto.getRole().name())
                .build();
    }

    public static IMapper<UserCreateDto, UserEntity> getInstance() {
        return INSTANCE;
    }
}
