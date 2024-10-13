package by.it_academy.jd2.service.validation.api;

import by.it_academy.jd2.service.dto.MessageCreateDto;
import by.it_academy.jd2.dao.entity.UserEntity;
import by.it_academy.jd2.service.validation.ValidationResult;

public interface IValidateMessage {

    ValidationResult isValid(MessageCreateDto message, UserEntity userEntity);
}
