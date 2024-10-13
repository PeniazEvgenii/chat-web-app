package by.it_academy.jd2.service.validation.api;

import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.validation.ValidationResult;

public interface IValidateForm {
    ValidationResult isValid(UserCreateDto userCreateDto, UserReadDto userReadDto);
}
