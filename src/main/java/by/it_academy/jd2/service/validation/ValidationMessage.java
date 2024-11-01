package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.dto.MessageCreateDto;
import by.it_academy.jd2.dao.entity.UserEntity;
import by.it_academy.jd2.service.validation.api.IValidateMessage;

public class ValidationMessage implements IValidateMessage {
    @Override
    public ValidationResult isValid(MessageCreateDto message, UserEntity userEntity) {
        ValidationResult validationResult = new ValidationResult();
        if(userEntity == null) {
            validationResult.addError(new Error("not_user", "The specified user does not exist", "Указанный пользователь не существует"));
        }

        String body = message.getBody();
        if(body != null && body.isBlank()) {
            validationResult.addError(new Error("not_message", "Message is empty", "Вы отправляете пустое сообщение"));
        }
        return validationResult;
    }
}
