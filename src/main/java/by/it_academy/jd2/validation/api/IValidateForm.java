package by.it_academy.jd2.validation.api;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.dto.UserReadDto;
import by.it_academy.jd2.validation.ValidationResult;

public interface IValidateForm {
    ValidationResult isValid(UserCreateDto userCreateDto, UserReadDto userReadDto);
}
