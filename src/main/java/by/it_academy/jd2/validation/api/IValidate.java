package by.it_academy.jd2.validation.api;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.validation.ValidationResult;

public interface IValidate {
    ValidationResult isValid(UserCreateDto userCreateDto);
}
