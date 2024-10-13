package by.it_academy.jd2.validation;

import by.it_academy.jd2.dto.MessageCreateDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.validation.api.IValidateMessage;

public class ValidationMessage implements IValidateMessage {
    @Override
    public ValidationResult isValid(MessageCreateDto message, UserEntity userEntity) {
        ValidationResult validationResult = new ValidationResult();
        if(userEntity == null) {
            validationResult.addError(new Error("not_user", "The specified user does not exist", "Указанный пользователь не существует"));
        }

        String body = message.getBody();
        if(body != null && body.isBlank()) {
            validationResult.addError(new Error("not_message", "Message is empty", "пустое сообщение"));
        }
        return validationResult;
    }
}
