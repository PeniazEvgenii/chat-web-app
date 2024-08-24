package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.MapperToUserEntity;
import by.it_academy.jd2.mapper.api.IMapper;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.UserStorage;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.validation.ValidationException;
import by.it_academy.jd2.validation.ValidationForm;
import by.it_academy.jd2.validation.ValidationResult;
import by.it_academy.jd2.validation.api.IValidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService implements IUserService {
    private static final IUserService INSTANCE = new UserService();

    private final IValidate validationForm = ValidationForm.getInstance();
    private final IMapper<UserCreateDto, UserEntity> mapperUser = MapperToUserEntity.getInstance();
    private final IUserStorage userStorage = UserStorage.getInstance();

    @Override
    public Long create(UserCreateDto userCreateDto) {
        List<UserEntity> allUsers = new ArrayList<>(getAllUsers().values());

        ValidationResult validationResult = validationForm.isValid(userCreateDto, allUsers);
        if (!validationResult.checkErrorEmpty()) {
            throw new ValidationException(validationResult.getErrors());
        }

        UserEntity userEntity = mapperUser.mapFrom(userCreateDto);

        return userStorage.create(userEntity);

    }

    @Override
    public Map<Long, UserEntity> getAllUsers() {
        return userStorage.getAll();
    }


    public static IUserService getInstance() {
        return INSTANCE;
    }
}
