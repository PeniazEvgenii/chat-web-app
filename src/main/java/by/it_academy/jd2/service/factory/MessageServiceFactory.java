package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.dao.factory.DaoFactory;
import by.it_academy.jd2.service.mapper.factory.MapperFactory;
import by.it_academy.jd2.service.MessageService;
import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.validation.factory.ValidationFactory;

public class MessageServiceFactory {
    private static final IMessageService messageService = new MessageService(
            UserServiceFactory.getUserService(),
            DaoFactory.getMessageDao(),
            ValidationFactory.getValidationMessage(),
            MapperFactory.getMessageMapper()
    );

    private MessageServiceFactory() {}

    public static IMessageService getMessageService() {
        return messageService;
    }
}
