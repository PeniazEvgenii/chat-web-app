package by.it_academy.jd2.validation.api;

import by.it_academy.jd2.dto.MessageCreateDto;
import by.it_academy.jd2.dto.UserReadDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.validation.ValidationResult;

public interface IValidateMessage {

    ValidationResult isValid(MessageCreateDto message, UserEntity userEntity);
}
