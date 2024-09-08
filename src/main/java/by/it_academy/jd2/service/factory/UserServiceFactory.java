package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.mapper.factory.MapperFactory;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.factory.UserStorageFactory;
import by.it_academy.jd2.validation.factory.ValidationFormFactory;

public class UserServiceFactory {
    private static final IUserService INSTANCE = new UserService(
            ValidationFormFactory.getInstance(),
            MapperFactory.getMapperToUserEntityInstance(),
            MapperFactory.getMapperToUserDtoInstance(),
            UserStorageFactory.getInstance()
    );

    private UserServiceFactory() {}

    public static IUserService getInstance() {
        return INSTANCE;
    }
}
