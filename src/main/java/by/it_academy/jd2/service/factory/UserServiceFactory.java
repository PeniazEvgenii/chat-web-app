package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.dao.factory.DaoFactory;
import by.it_academy.jd2.service.mapper.factory.MapperFactory;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.validation.factory.ValidationFactory;

public class UserServiceFactory {
    private static final IUserService userService = new UserService(
            ValidationFactory.getValidationForm(),
            MapperFactory.getUserMapper(),
            DaoFactory.getUserDao()
    );

    private UserServiceFactory() {}

    public static IUserService getUserService() {
        return userService;
    }
}
