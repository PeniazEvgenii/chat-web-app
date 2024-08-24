package by.it_academy.jd2.validation;

import by.it_academy.jd2.dto.UserCreateDto;
import by.it_academy.jd2.util.DateFormatUtil;
import by.it_academy.jd2.validation.api.IValidate;

public class ValidationForm implements IValidate {
    private static final IValidate INSTANCE = new ValidationForm();

    /**
     * Метод для валидации информации, переданной из формы регистрации
     * @param userCreateDto обдъект с пользовательской информацией
     * @return ValidationResult результат проверки корректности данных 
     */
    @Override
    public ValidationResult isValid(UserCreateDto userCreateDto) {
        ValidationResult validationResult = new ValidationResult();

        if(userCreateDto.getLogin().isBlank()) {
            validationResult.addError(new Error("invalid_login","Login is empty or consists only of spaces"));
        }

        if(userCreateDto.getPassword().isBlank()) {
            validationResult.addError(new Error("invalid_password","Password is empty or consists only of spaces"));
        }

        if(userCreateDto.getName().isBlank()) {
            validationResult.addError(new Error("invalid_name","Name is empty or consists only of spaces"));
        }

        if(!DateFormatUtil.isValidDate(userCreateDto.getBirthDate())) {
            validationResult.addError(new Error("invalid_birthDate","BirthDate is incorrect"));
        }

        return validationResult;
    }

    public static IValidate getInstance() {
        return INSTANCE;
    }
}
