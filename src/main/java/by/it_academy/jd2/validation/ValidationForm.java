package by.it_academy.jd2.validation;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.util.DateFormatUtil;
import by.it_academy.jd2.validation.api.IValidate;

import java.util.List;
import java.util.Optional;

public class ValidationForm implements IValidate {

    public ValidationForm() {}

    /**
     * Метод для валидации информации, переданной из формы регистрации
     * @param userCreateDto обдъект с пользовательской информацией
     * @return ValidationResult результат проверки корректности данных 
     */
    @Override
    public ValidationResult isValid(UserCreateDto userCreateDto, UserEntity userByLogin) {
        ValidationResult validationResult = new ValidationResult();
        if(userByLogin != null) {
            validationResult.addError(new Error("not_unique_login","This login already exists",
                    "Пользователь с таким логином уже существует"));
        }

        if(userCreateDto.getLogin().isBlank()) {
            validationResult.addError(new Error("invalid_login","Login is empty or consists only of spaces",
                    "Логин не заполнен либо состоит только из пробелов"));
        }

        if(userCreateDto.getPassword().isBlank()) {
            validationResult.addError(new Error("invalid_password","Password is empty or consists only of spaces",
                    "Пароль не заполнен либо состоит только из пробелов"));
        }

        if(userCreateDto.getName().isBlank()) {
            validationResult.addError(new Error("invalid_name","Name is empty or consists only of spaces",
                    "ФИО не заполнено либо состоит только из пробелов"));
        }

        if(!DateFormatUtil.isValidDate(userCreateDto.getBirthDate())) {
            validationResult.addError(new Error("invalid_birthDate","BirthDate is incorrect",
                    "Дата рождения некорректна"));
        }

        return validationResult;
    }


}
