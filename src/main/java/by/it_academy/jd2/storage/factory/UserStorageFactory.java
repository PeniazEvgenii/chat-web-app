package by.it_academy.jd2.storage.factory;

import by.it_academy.jd2.storage.UserStorage;
import by.it_academy.jd2.storage.api.IUserStorage;

public class UserStorageFactory {
    public static final IUserStorage INSTANCE = new UserStorage();

    private UserStorageFactory() {}

    public static IUserStorage getInstance() {
        return INSTANCE;
    }
}
