package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.mapper.MapperUser;
import by.it_academy.jd2.mapper.api.IMapper;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.UserStorage;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.validation.ValidationException;
import by.it_academy.jd2.validation.ValidationForm;
import by.it_academy.jd2.validation.ValidationResult;
import by.it_academy.jd2.validation.api.IValidate;

public class UserService implements IUserService {
    private static final IUserService INSTANCE = new UserService();

    private final IValidate validationForm = ValidationForm.getInstance();
    private final IMapper<UserCreateDto, UserEntity> mapperUser = MapperUser.getInstance();
    private final IUserStorage userStorage = UserStorage.getInstance();

    @Override
    public Long create(UserCreateDto userCreateDto) {
        ValidationResult validationResult = validationForm.isValid(userCreateDto);
        if (!validationResult.checkErrorEmpty()) {
            throw new ValidationException(validationResult.getErrors());
        }

        UserEntity userEntity = mapperUser.mapFrom(userCreateDto);

        return userStorage.create(userEntity);

    }

    public static IUserService getInstance() {
        return INSTANCE;
    }
}
