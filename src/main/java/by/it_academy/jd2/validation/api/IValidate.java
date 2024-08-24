package by.it_academy.jd2.validation.api;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.validation.ValidationResult;

import java.util.List;

public interface IValidate {
    ValidationResult isValid(UserCreateDto userCreateDto, List<UserEntity> allUsers);
}
