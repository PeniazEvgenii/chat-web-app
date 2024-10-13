package by.it_academy.jd2.service.mapper.factory;

import by.it_academy.jd2.service.mapper.MessageMapper;
import by.it_academy.jd2.service.mapper.api.IMessageMapper;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import by.it_academy.jd2.service.mapper.UserMapper;

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
