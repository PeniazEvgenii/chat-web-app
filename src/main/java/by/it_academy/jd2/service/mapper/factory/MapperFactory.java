package by.it_academy.jd2.mapper.factory;

import by.it_academy.jd2.mapper.MessageMapper;
import by.it_academy.jd2.mapper.api.IMessageMapper;
import by.it_academy.jd2.mapper.api.IUserMapper;
import by.it_academy.jd2.mapper.UserMapper;

public class MapperFactory {
    private static final IUserMapper userMapper = new UserMapper();
    private static final IMessageMapper messageMapper = new MessageMapper(userMapper);

    private MapperFactory() {}

    public static IUserMapper getUserMapper() {
        return userMapper;
    }

    public static IMessageMapper getMessageMapper() {
        return messageMapper;
    }
}
