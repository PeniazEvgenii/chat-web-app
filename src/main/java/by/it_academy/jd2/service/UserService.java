package by.it_academy.jd2.service;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserReadDto;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.api.IUserMapper;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.validation.ValidationException;
import by.it_academy.jd2.validation.ValidationResult;
import by.it_academy.jd2.validation.api.IValidate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService implements IUserService {

    private final IValidate validationForm;
    private final IUserMapper userMapper;
    private final IUserDao userDao;

    public UserService(IValidate validationForm,
                       IUserMapper userMapper,
                       IUserDao userDao) {
        this.validationForm = validationForm;
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    @Override
    public UserEntity create(UserCreateDto userCreateDto) {
        UserReadDto userByLogin = getByLogin(userCreateDto.getLogin()).orElse(null);

        ValidationResult validationResult = validationForm.isValid(userCreateDto, userByLogin);
        if (!validationResult.checkErrorEmpty()) {
            throw new ValidationException(validationResult.getErrors());
        }

        UserEntity userEntity = userMapper.mapDtoToEntity(userCreateDto);

        return userDao.create(userEntity);   //надо на сущность поменять

    }

    @Override
    public List<UserReadDto> getAll() {
        return userDao.getAll().stream()
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserReadDto> login(UserLoginDto userLoginDto) {
       return userDao.getByPassLogin(userLoginDto)
               .map(userMapper::mapEntityToDto);
    }

    public Optional<UserReadDto> getByLogin(String login) {     //может еще надо и получение по логину Entity для сообщеия
        return userDao.getByLogin(login)
                .map(userMapper::mapEntityToDto);
    }





}
