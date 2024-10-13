package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.util.DateFormatUtil;
import by.it_academy.jd2.service.validation.api.IValidateForm;

public class ValidationForm implements IValidateForm {
    private static final int MAX_LENGTH_LOGIN = 64;
    private static final int MAX_LENGTH_PASSWORD = 64;

    public ValidationForm() {}

    /**
     * Метод для валидации информации, переданной из формы регистрации
     * @param userCreateDto обдъект с пользовательской информацией
     * @return ValidationResult результат проверки корректности данных 
     */
    @Override
    public ValidationResult isValid(UserCreateDto userCreateDto, UserReadDto userByLogin) {
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

        if(userCreateDto.getLogin().length() > MAX_LENGTH_LOGIN) {
            validationResult.addError(new Error("invalid_length_login","Login length is no more than 64 characters",
                    "Длина логина должна превышать 64 символа"));
        }

        if(userCreateDto.getPassword().length() > MAX_LENGTH_PASSWORD) {
            validationResult.addError(new Error("invalid_length_password","Password length is no more than 64 characters",
                    "Длина пароля должна превышать 64 символа"));
        }

        return validationResult;
    }


}
