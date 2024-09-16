package by.it_academy.jd2.service;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserDto;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.api.IUserMapper;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.validation.ValidationException;
import by.it_academy.jd2.validation.ValidationResult;
import by.it_academy.jd2.validation.api.IValidate;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final IValidate validationForm;
    private final IUserMapper userMapper;
    private final IUserDao userStorage;

    public UserService(IValidate validationForm,
                       IUserMapper userMapper,
                       IUserDao userStorage) {
        this.validationForm = validationForm;
        this.userMapper = userMapper;
        this.userStorage = userStorage;
    }

    @Override
    public Long create(UserCreateDto userCreateDto) {
        Optional<UserEntity> userByLogin = userStorage.getUserByLogin(userCreateDto.getLogin());

        ValidationResult validationResult = validationForm.isValid(userCreateDto, userByLogin.orElse(null));
        if (!validationResult.checkErrorEmpty()) {
            throw new ValidationException(validationResult.getErrors());
        }

        UserEntity userEntity = userMapper.mapDtoToEntity(userCreateDto);

        return userStorage.create(userEntity).getId();   //надо на сущность поменять

    }

    @Override
    public List<UserEntity> getAll() {
        return userStorage.getAll();
    }

    @Override
    public Optional<UserDto> login(UserLoginDto userLoginDto) {
       return userStorage.getUserByPassLogin(userLoginDto)
               .map(userMapper::mapEntityToDto);
    }



}
