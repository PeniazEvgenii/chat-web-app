package by.it_academy.jd2.service.validation.factory;

import by.it_academy.jd2.service.validation.ValidationForm;
import by.it_academy.jd2.service.validation.ValidationMessage;
import by.it_academy.jd2.service.validation.api.IValidateForm;
import by.it_academy.jd2.service.validation.api.IValidateMessage;

public class ValidationFactory {
    private static final IValidateForm validationForm = new ValidationForm();
    private static final IValidateMessage validationMessage = new ValidationMessage();

    private ValidationFactory() {}

    public static IValidateForm getValidationForm() {
        return validationForm;
    }

    public static IValidateMessage getValidationMessage() {
        return validationMessage;
    }

}
