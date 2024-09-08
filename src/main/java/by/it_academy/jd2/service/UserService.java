package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.api.IMapper;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.validation.ValidationException;
import by.it_academy.jd2.validation.ValidationResult;
import by.it_academy.jd2.validation.api.IValidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService implements IUserService {

    private final IValidate validationForm;
    private final IMapper<UserCreateDto, UserEntity> mapperUser;
    private final IMapper<UserEntity, UserDto> mapperToUserDto;
    private final IUserStorage userStorage;

    public UserService(IValidate validationForm,
                       IMapper<UserCreateDto, UserEntity> mapperUser,
                       IMapper<UserEntity, UserDto> mapperToUserDto,
                       IUserStorage userStorage) {
        this.validationForm = validationForm;
        this.mapperUser = mapperUser;
        this.mapperToUserDto = mapperToUserDto;
        this.userStorage = userStorage;
    }

    @Override
    public Long create(UserCreateDto userCreateDto) {
        List<UserEntity> allUsers = new ArrayList<>(getAll().values());

        ValidationResult validationResult = validationForm.isValid(userCreateDto, allUsers);
        if (!validationResult.checkErrorEmpty()) {
            throw new ValidationException(validationResult.getErrors());
        }

        UserEntity userEntity = mapperUser.mapFrom(userCreateDto);

        return userStorage.create(userEntity);

    }

    @Override
    public Map<Long, UserEntity> getAll() {
        return userStorage.getAll();
    }

    @Override
    public Optional<UserDto> login(UserLoginDto userLoginDto) {
       return userStorage.getUserByPassLogin(userLoginDto)
               .map(mapperToUserDto::mapFrom);
    }



}
